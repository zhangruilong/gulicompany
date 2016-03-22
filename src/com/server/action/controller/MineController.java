package com.server.action.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CustomerMapper;
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
	//修改客户信息
	@RequestMapping("/guliwang/doEditCus")
	public String doEditCus(Model model,Customer customer){
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerid());
		model.addAttribute("customer", customer);
		return "myshop.jsp";
	}
	//修改客户信息
	@RequestMapping("/guliwang/editCus")
	public String editCus(HttpSession session,Customer customer){
		
		return "";
	}
}
