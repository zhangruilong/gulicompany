package com.server.action.controller;

import java.util.List;

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
		List<Emp> empli = empMapper.selectEmpLogin(loginname,password);
		if(null != empli && empli.size()>0 && null != company){		//经销商和业务员的账号密码重复
			model.addAttribute("msg","无效的账号和密码。");
		} else if(null != company){
			info.setCompanyid(company.getCompanyid());
			info.setUsername(company.getUsername());		//操作人名称
			info.setLoginname(loginname);
			info.setCompanycode(company.getCompanycode());
			info.setCompanyshop(company.getCompanyshop());	//经销商名称
			info.setComusername(company.getUsername());		//经销商的联系人姓名
			info.setCompanyphone(company.getCompanyphone());//经销商手机
			info.setEmpcode(company.getUsername());				//录单时的订单源
			info.setPower("company");
			
			session.setAttribute("loginInfo", info);
			session.setMaxInactiveInterval(36000);
			model.addAttribute("msg","success");
		} else if(null != empli && empli.size()>0){
			Emp emp = empli.get(0);
			Company empCom = companyMapper.selectByPrimaryKey(emp.getEmpcompany());
			info.setCompanyid(emp.getEmpcompany());
			info.setUsername(emp.getEmpcode());		//操作人名称
			info.setLoginname(loginname);
			info.setCompanycode(empCom.getCompanycode());
			info.setCompanyshop(empCom.getCompanyshop());	//经销商名称
			info.setPower(emp.getEmpcode());
			info.setComusername(empCom.getUsername());		//经销商的联系人姓名
			info.setCompanyphone(empCom.getCompanyphone());//经销商手机
			info.setEmpcode(emp.getEmpcode());			//录单时的订单源
			
			session.setAttribute("loginInfo", info);
			session.setMaxInactiveInterval(36000);
			model.addAttribute("msg","success");
		} else {
			model.addAttribute("msg","账号或密码不正确。");
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
