package com.server.action.controller;

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

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.system.tools.util.CommonUtil;
@Controller
public class LoginController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AddressMapper addressMapper;
	//登录
	@RequestMapping("/guliwang/login")
	public String login(HttpSession session,Customer customer){
		Customer customer2 = customerMapper.selectByPhone(customer);
			session.setAttribute("customer", customer2);
			return "redirect:login.jsp";
	}
	//注册页面
	@RequestMapping("/guliwang/doReg")
	public String doReg(Model model){
		List<City> cityList = cityMapper.selectAllCity();
		List<City> cityParents = cityMapper.selectAllParent();
		model.addAttribute("cityList", cityList);
		model.addAttribute("cityParents", cityParents);
		return "forward:reg.jsp";
	}
	//注册用户
	@RequestMapping("/guliwang/reg")
	public String reg(Customer customer,String addressphone){
		String newCusId = CommonUtil.getNewId();
		customer.setCustomerid(newCusId);		//设置新id
		customerMapper.insertSelective(customer);		//添加新客户
		//添加新地址
		Address address = new Address();
		address.setAddressture(1);							//自动设为默认地址
		address.setAddressid(CommonUtil.getNewId());		//设置新id
		address.setAddressaddress(customer.getCustomeraddress());
		address.setAddresscustomer(newCusId);
		address.setAddressphone(addressphone);
		address.setAddressconnect(customer.getCustomername());
		addressMapper.insertSelective(address);				//添加默认地址
		return "redirect:login.jsp";
	}
	//注销登录
	@RequestMapping("/guliwang/loginOut")
	public String loginOut(HttpSession session){
		session.invalidate();
		return "redirect:login.jsp";
	}
}
