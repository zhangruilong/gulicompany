package com.server.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Orderd;
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
		List<Ccustomer> ccustomerList = ccustomerMapper.selectCusByCom(ccustomerCon,pagenow,10);
		model.addAttribute("ccustomerList", ccustomerList);
		model.addAttribute("ccustomerCon", ccustomerCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/customerMana.jsp";
	}
	//修改客户信息页面
	@RequestMapping(value="/companySys/queryCcusAndCus",produces="application/json")
	@ResponseBody
	public Map<String,Object> queryCcusAndCus(String customerid,String ccustomerid){
		Map<String,Object> map = new HashMap<String, Object>();
		Customer customer = customerMapper.selectByPrimaryKey(customerid);
		Ccustomer ccustomer = ccustomerMapper.selectByPrimaryKey(ccustomerid);
		map.put("editCus", customer);
		map.put("editCcus", ccustomer);
		return map;
	}
	//修改客户信息
	@RequestMapping(value="/companySys/editCcusAndCus",produces="application/json")
	@ResponseBody
	public Map<String,Object> editCcusAndCus(Customer customer,Ccustomer ccustomer){
		Map<String,Object> map = new HashMap<String, Object>();
		customer.setCustomerlevel(Integer.parseInt(ccustomer.getCcustomerdetail()));
		customer.setUpdtime(DateUtils.getDateTime());				//设置客户修改时间
		customerMapper.updateByPrimaryKeySelective(customer);
		ccustomerMapper.updateByPrimaryKeySelective(ccustomer);
		return map;
	}
	//导出报表
	@RequestMapping("/companySys/exportCustomerReport")
	@ResponseBody
	public void exportCustomerReport(HttpServletResponse response,Ccustomer ccustomerCon) throws Exception{
		ArrayList<Customer> list = (ArrayList<Customer>) customerMapper.selectCustomerByGuanxi(ccustomerCon);
		String[] heads = {"客户编码","客户姓名","手机","店铺","城市","县","街道地址","类型","等级","创建时间","修改时间"};				//表头
		String[] discard = {"customerid","customerpsw","openid","customerdetail","customerstatue","orderm","collectList"};			//要忽略的字段名
		String name = "客户统计报表";							//文件名称
		FileUtil.expExcel(response, list, heads, discard, name);
	}
}














