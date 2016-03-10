package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CcustomerMapper;
import com.server.pojo.entity.Ccustomer;

/**
 * 供应商后台管理系统-客户管理
 * @author tao
 *
 */
@Controller
public class Com_customerCtl {
	@Autowired
	private CcustomerMapper ccustomerMapper;
	//全部客户
	@RequestMapping("/companySys/allCustomer")
	public String allCustomer(Model model,Ccustomer ccustomerCon){
		List<Ccustomer> ccustomerList = ccustomerMapper.selectCusByCom(ccustomerCon);
		model.addAttribute("ccustomerList", ccustomerList);
		model.addAttribute("ccustomerCon", ccustomerCon);
		return "forward:/companySys/customerMana.jsp";
	}
}
