package com.server.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.EmpMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.CustomerStatisticVO;
import com.server.pojo.entity.Emp;
import com.server.pojo.entity.LoginInfo;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

/**
 * 供应商后台管理系统-客户管理
 * @author tao
 *
 */
@Controller
public class Com_customerCtl {
	@Autowired
	private CcustomerMapper ccustomerMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private CityMapper cityMapper;
	
	//一段时间内有订单的业务员
	@RequestMapping(value="/companySys/queryTimeEmp")
	@ResponseBody
	public Map<String,Object> queryTimeEmp(String staTime,String endTime,String companyid){
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> empLi = ccustomerMapper.selectTimeEmpName(staTime, endTime, companyid);
		if(empLi.size()>0){
			map.put("empLi", empLi);
			map.put("msg", "success");
		} else {
			map.put("msg", "error");
		}
		
		return map;
	}
	
	//新增录单客户时用到的city
	@RequestMapping(value="/companySys/addLGCity")
	@ResponseBody
	public Map<String,Object> addLGCity(String cityid){
		Map<String,Object> map = new HashMap<String, Object>();
		City city = cityMapper.selectByPrimaryKey(cityid);
		City cityparent = cityMapper.selectByPrimaryKey(city.getCityparent());
		map.put("city", cityparent);
		return map;
	}
	//全部客户
	@RequestMapping(value="/companySys/allCustomer")
	public String allCustomer(Model model,Ccustomer ccustomerCon,Integer pagenow){
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = ccustomerMapper.selectCusByComCount(ccustomerCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Ccustomer> ccustomerList = ccustomerMapper.selectCusByCom(ccustomerCon,pagenow,10);
		model.addAttribute("ccustomerList", ccustomerList);
		model.addAttribute("ccustomerCon", ccustomerCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		if(ccustomerCon.getCreator() != null && ccustomerCon.getCreator().equals("1")){
			return "forward:/companySys/largeCusMana.jsp";
		} else {
			return "forward:/companySys/customerMana.jsp";
		}
	}
	//修改客户信息页面
	@RequestMapping(value="/companySys/queryCcusAndCus",produces="application/json")
	@ResponseBody
	public Map<String,Object> queryCcusAndCus(String ccustomerid){
		Map<String,Object> map = new HashMap<String, Object>();
		Ccustomer ccustomer = ccustomerMapper.selectCCusAndCusById(ccustomerid);
		Customer editCus = ccustomer.getCustomer();
		List<Emp> empList = empMapper.selectEmpByCompany(ccustomer.getCcustomercompany());
		map.put("emps", empList);
		map.put("editCus", editCus);
		map.put("editCcus", ccustomer);
		return map;
	}
	//修改客户信息
	@RequestMapping(value="/companySys/editCcusAndCus",produces="application/json")
	@ResponseBody
	public Map<String,Object> editCcusAndCus(HttpSession session,Customer customer,Ccustomer ccustomer,String accountManager){
		Map<String,Object> map = new HashMap<String, Object>();
		LoginInfo loInfo = (LoginInfo) session.getAttribute("loginInfo");
		String currDatetime = DateUtils.getDateTime();			//当前时间
		customer.setCustomerlevel(Integer.parseInt(ccustomer.getCcustomerdetail()));
		customer.setUpdtime(currDatetime);				//设置客户修改时间
		ccustomer.setCreatetime(accountManager);
		List<Address> addList = addressMapper.selectByCondition(customer.getCustomerid());
		Address add = null;
		if(addList != null && addList.size() >0){
			add = addList.get(0);
		} else {
			add = new Address();
			add.setAddressid(customer.getCustomerid());
			add.setAddresscustomer(customer.getCustomerid());
		}
		add.setAddressaddress(customer.getCustomercity()+customer.getCustomerxian()+customer.getCustomeraddress());
		add.setAddressconnect(customer.getCustomername());
		add.setAddressphone(customer.getCustomerphone());
		add.setAddressture(1);
		addressMapper.updateByPrimaryKeySelective(add);
		customerMapper.updateByPrimaryKeySelective(customer);
		ccustomer.setCcustomerupdtime(currDatetime);			//客户关系的修改时间
		ccustomer.setCcustomerupdor(loInfo.getUsername());		//修改人
		ccustomerMapper.updateByPrimaryKeySelective(ccustomer);
		return map;
	}
	//导出报表
	@RequestMapping("/companySys/exportCustomerReport")
	@ResponseBody
	public void exportCustomerReport(HttpServletResponse response,Ccustomer ccustomerCon) throws Exception{
		ArrayList<Customer> list = (ArrayList<Customer>) customerMapper.selectCustomerByGuanxi(ccustomerCon);
		String[] heads = {"客户编码","客户姓名","手机","客户经理","店铺","城市","县","街道地址","类型","等级","修改人","修改时间","创建时间"};				//表头
		String[] discard = {"customerid","openid","customerdetail","orderm","collectList"};			//要忽略的字段名
		String name = "客户统计报表";							//文件名称
		FileUtil.expExcel(response, list, heads, discard, name);
	}
	//录单客户下单页信息
	@RequestMapping("/companySys/largeCusXiaDanInfo")
	@ResponseBody
	public Map<String, Object> largeCusXiaDanInfo(String ccustomerid){
		Map<String, Object> map = new HashMap<String, Object>();
		Address address = new Address();
		Ccustomer largeCCus = ccustomerMapper.selectCCusAndCusById(ccustomerid);
		address.setAddresscustomer(largeCCus.getCcustomercustomer());
		List<Address> addressList = addressMapper.selectDefAddress(address);
		if(addressList != null && addressList.size()>0){
			map.put("cusAddress", addressList.get(0));
		}
		map.put("largeCCus", largeCCus);
		return map;
	}
	//得到客户全部地址
	@RequestMapping("/companySys/queryLargeCusAllAddress")
	@ResponseBody
	public List<Address> queryLargeCusAllAddress(String customerid){
		List<Address> addressList = addressMapper.selectByCondition(customerid);
		return addressList;
	}
	//统计客户
	@RequestMapping("/companySys/cusStatistic")
	public String cusStatistic(HttpSession session,Model model,String companyid,String staCusQuery,Integer nowpage,String staTime,String endTime){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String today = DateUtils.getDate();
		if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		} else if((staTime == null && endTime == null) || (staTime.equals("") && endTime.equals("")) ){		//如果开始和结束时间为null就设置为当天时间
			staTime = DateUtils.getDate() + " 00:00:00";
			endTime = DateUtils.getDate() + " 23:59:59";
		}
		if(nowpage == null){
			nowpage = 1;
		}
		Integer count = customerMapper.selectCusStatisticCount(companyid, staCusQuery,staTime, endTime,info.getPower(),today);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		if(nowpage > pageCount){
			nowpage = pageCount;
		}
		List<CustomerStatisticVO> volist = customerMapper.selectCusStatistic(companyid,staCusQuery, nowpage, 10,staTime, endTime,info.getPower(),today);
		CustomerStatisticVO total = customerMapper.selectStatisticSum(companyid, staCusQuery, staTime, endTime,info.getPower(),today);
		model.addAttribute("total", total);
		model.addAttribute("cusStaVoList", volist);
		model.addAttribute("staCusQuery", staCusQuery);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("nowpage", nowpage);
		model.addAttribute("count", count);
		return "forward:cusStatistic.jsp";
	}
	//导出客户统计报表
	@RequestMapping("/companySys/exportCusStatisticReport")
	@ResponseBody
	public void exportCusStatisticReport(HttpSession session,HttpServletResponse response,String companyid,String staCusQuery,
			String staTime,String endTime) throws Exception{
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String today = DateUtils.getDate();
		ArrayList<CustomerStatisticVO> list = (ArrayList<CustomerStatisticVO>) customerMapper.selectCusStatisticReport(
				companyid, staCusQuery, staTime, endTime,info.getPower(),today);
		String[] heads = {"联系人","手机","店名","地址","订单数量","订单总额","客户经理"};								//表头
		String[] discard = {"customerid","customercode","customerpsw","customercity","customerxian",
					"customertype","customerlevel","openid","customerdetail",
					"customerstatue","createtime","updtime","collectList",};			//要忽略的字段名
		String name = "客户订单统计报表";																				//文件名称
		if(!staTime.equals("") && !endTime.equals("")){
			name = staTime + "日至" + endTime + "日的" + name;
		} else if(staTime.equals("") && !endTime.equals("")){
			name = endTime + "日之前的" + name;
		} else if(endTime.equals("") && !staTime.equals("")){
			name = staTime + "日之后的" + name;
		}
		FileUtil.expExcel(response, list, heads, discard, name);
	}
}














