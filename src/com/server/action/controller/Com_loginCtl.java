package com.server.action.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CompanyMapper;
import com.server.pojo.entity.Company;

/**
 * 供应商后台管理系统-登录
 * @author tao
 *
 */
@Controller
public class Com_loginCtl {
	@Autowired
	private CompanyMapper companyMapper;
	//登录
	@RequestMapping("/companySys/login")
	public String login(HttpSession session,Company company,Model model){
		Company company2 = companyMapper.selectLogin(company);
		session.setAttribute("company", company2);
		session.setMaxInactiveInterval(36000);
		if(null == company2){
			model.addAttribute("msg", "密码或账号不正确。");
		}
		return "forward:/companySys/login.jsp";
	}
	//注销
	@RequestMapping("/companySys/loginOut")
	public String loginOut(HttpSession session){
		session.invalidate();
		return "redirect:/companySys/login.jsp";
	}
}
