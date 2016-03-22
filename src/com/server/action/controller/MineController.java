package com.server.action.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;

/**
 * 我的
 * @author taolichao
 *
 */
@Controller
public class MineController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CityMapper cityMapper;
	//到修改页
	@RequestMapping("/guliwang/doEditCus")
	public String doEditCus(Model model,Customer customer){
		List<City> cityList = cityMapper.selectAllParent();						//查询所有地区的父类
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerid());	
		model.addAttribute("customer", customer);
		model.addAttribute("cityList", cityList);
		return "myshop.jsp";
	}
	//修改客户信息
	@RequestMapping("/guliwang/editCus")
	public String editCus(HttpSession session,Customer customer){
		customerMapper.updateByPrimaryKeySelective(customer);
		session.setAttribute("customer", customer);
		return "mine.jsp";
	}
}
