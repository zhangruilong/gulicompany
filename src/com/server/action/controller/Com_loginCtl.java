package com.server.action.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.EmpMapper;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Emp;
import com.server.pojo.entity.LoginInfo;

/**
 * 供应商后台管理系统-登录
 * @author tao
 *
 */
@Controller
public class Com_loginCtl {
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private EmpMapper empMapper;
	//登录
	@RequestMapping("/companySys/login")
	public String login(HttpSession session,String loginname,String password,Model model){
		LoginInfo info = new LoginInfo();
		Company company = companyMapper.selectLogin(loginname,password);
		Emp emp = empMapper.selectEmpLogin(loginname,password);
		if(null != company){
			info.setCompanyid(company.getCompanyid());
			info.setUsername(company.getCompanyshop());	//经销商名称
			info.setLoginname(loginname);
			info.setCompanycode(company.getCompanycode());
			info.setCompanyshop(company.getCompanyshop());
			info.setPower("company");
			
			session.setAttribute("loginInfo", info);
			session.setMaxInactiveInterval(36000);
		} else if(null != emp){
			Company empCom = companyMapper.selectByPrimaryKey(emp.getEmpcompany());
			info.setCompanyid(emp.getEmpcompany());
			info.setUsername(emp.getEmpdetail());		//业务员姓名
			info.setLoginname(loginname);
			info.setCompanycode(empCom.getCompanycode());
			info.setCompanyshop(empCom.getCompanyshop());
			info.setPower("emp");
			
			session.setAttribute("loginInfo", info);
			session.setMaxInactiveInterval(36000);
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
