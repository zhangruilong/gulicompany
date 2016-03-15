package com.server.action.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CompanyMapper;
import com.server.pojo.entity.Company;

/**
 * 供应商后台管理系统-系统管理
 * @author tao
 *
 */
@Controller
public class Com_sysManaCtl {
	@Autowired
	private CompanyMapper companyMapper;
	//修改供应商信息
	@RequestMapping("/companySys/editCompInfo")
	public String editCompInfo(HttpSession session,Model model,Company company){
		companyMapper.updateByPrimaryKeySelective(company);
		session.setAttribute("company", company);
		model.addAttribute("message", "信息已修改");
		return "forward:/companySys/cusInfo.jsp";
	}
	//修改密码
	@RequestMapping("/companySys/editPwd")
	public String editPwd(HttpSession session,Model model,Company company,String newpassword){
		company = companyMapper.selectLogin(company);
		if(company == null){
			model.addAttribute("message", "密码不正确");
		} else {
			company.setPassword(newpassword);
			companyMapper.updateByPrimaryKeySelective(company);
			session.setAttribute("company", company);
			model.addAttribute("message", "密码已修改");
		}
		return "forward:/companySys/editPas.jsp";
	}
}
