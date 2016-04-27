package com.server.action.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 登录
 * @author taolichao
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
@Controller
public class LoginController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CcustomerMapper ccustomreMapper;
	
	//根据openid查询客户信息
	
	//登录
	@RequestMapping("/guliwang/login")
	public String login(HttpSession session,Customer customer){
		customer.setCustomerphone("15645566879");
		customer.setCustomerpsw("1");				//这是默认的账号和密码
		customer = customerMapper.selectByPhone(customer);
		session.setAttribute("customer", customer);
		return "redirect:/guliwangemp/index.jsp";
	}
	//注册页面
	@RequestMapping("/guliwang/doReg")
	public String doReg(Model model){
		List<City> cityList = cityMapper.selectAllParent();
		model.addAttribute("cityList", cityList);
		return "forward:reg.jsp";
	}
	//注册用户
	@RequestMapping(value="/guliwang/reg" ,produces = "application/json")
	@ResponseBody
	public Customer reg(Customer customer,String addressphone){
		String newCusId = CommonUtil.getNewId();
		
		customer.setCustomerid(newCusId);		//设置新id
		customer.setCustomerstatue("启用");
		customer.setCustomerlevel(3);
		customer.setCustomertype("3");
		customer.setCreatetime(DateUtils.getDateTime());
		customerMapper.insertSelective(customer);		//添加新客户
		//添加新地址
		Address address = new Address();
		address.setAddressture(1);							//自动设为默认地址
		address.setAddressid(newCusId);		//设置新id
		address.setAddressaddress(customer.getCustomercity()+customer.getCustomerxian()+customer.getCustomeraddress());
		address.setAddresscustomer(newCusId);				//客户id
		address.setAddressphone(customer.getCustomerphone());
		address.setAddressconnect(customer.getCustomername());
		addressMapper.insertSelective(address);				//添加默认地址
		//添加与唯一客户的关系
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Ccustomer newccustomer = new Ccustomer();
		newccustomer.setCcustomerid(newCusId);
		newccustomer.setCcustomercompany("1");
		newccustomer.setCcustomercustomer(newCusId);
		newccustomer.setCcustomerdetail("3");
		newccustomer.setCreatetime(sdf.format(new Date()));
		ccustomreMapper.insertSelective(newccustomer);
		return customer;
	}
	//注销登录
	@RequestMapping("/guliwang/loginOut")
	public String loginOut(HttpSession session){
		session.invalidate();
		return "redirect:login.jsp";
	}
	//查询地区
	@RequestMapping(value="/guliwang/querycity", produces="application/json")
	@ResponseBody 
	public List<City> querycity(City cityNameOrKey){
		List<City> cityList = null;
		if(cityNameOrKey.getCityid() != null || cityNameOrKey.getCityname() != null ){
			City parentCity = cityMapper.selectByCitynameOrKey(cityNameOrKey).get(0);
			cityList = cityMapper.selectByCityparent(parentCity.getCityid());
		}
		return cityList;
	}
}


















