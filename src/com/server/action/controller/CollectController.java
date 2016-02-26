package com.server.action.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.Customer;

@Controller
public class CollectController {
	@Autowired
	private CustomerMapper customerMapper;
	
	@RequestMapping("/guliwang/doCollect")
	public String doCollect(Model model,String comid){
		Customer customer = customerMapper.selectCollectGoodsById(comid);
		model.addAttribute("customerCollect", customer);
		return "forward:/guliwang/collect.jsp";
	}
}
