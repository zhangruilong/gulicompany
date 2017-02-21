package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server.poco.CompanyPoco;
import com.server.poco.EmpPoco;
import com.server.pojo.Company;
import com.server.pojo.Emp;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;

/**
 * 供应商相关的业务逻辑
 *@author taolichao
 */
public class CPCompanyAction extends CompanyAction {
	
	//注销
	public void zhuxiao(HttpServletRequest request, HttpServletResponse response){
		logout(request, response);
	}
	//修改密码
	@SuppressWarnings("unchecked")
	public void editPWD(HttpServletRequest request, HttpServletResponse response){
		String newpassword = request.getParameter("newpassword");
		String password = request.getParameter("password");
		LoginInfo info = (LoginInfo) request.getSession().getAttribute("loginInfo");
		if(info.getPower().equals("emp")){
			List<Emp> empli = selAll(Emp.class,"select * from emp where LOGINNAME='"+info.getLoginname()+"' and PASSWORD='"+password+"'");
			if(empli.size()>0){
				Emp emp = empli.get(0);
				emp.setPassword(newpassword);
				result = updSingle(emp,EmpPoco.KEYCOLUMN);
			}
		} else if(info.getPower().equals("company")){
			List<Company> comli = selAll(Company.class,
					"select * from Company where LOGINNAME='"+info.getLoginname()+"' and PASSWORD='"+password+"'");
			if(comli.size()>0){
				Company com = comli.get(0);
				com.setPassword(newpassword);
				result = updSingle(com,CompanyPoco.KEYCOLUMN);
			}
		}
		responsePW(response, result);
	}
	//修改供应商信息
	public void editComInfo(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Company company:cuss){
			result = updSingle(company,CompanyPoco.KEYCOLUMN);
			if(result.equals(CommonConst.SUCCESS)){
				lif.setComusername(company.getUsername());
				lif.setCompanyshop(company.getCompanyshop());
				lif.setCompanyphone(company.getCompanyphone());
				request.getSession().setAttribute("loginInfo", lif);
			}
		}
		responsePW(response, result);
	}
	//登录
	@SuppressWarnings("unchecked")
	public void comlogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		LoginInfo info = new LoginInfo();
		List<Company> comLi = (List<Company>) selAll(Company.class, 
				"select * from company where loginname='"+loginname+"' and password='"+password+"'");
		List<Emp> empli = (List<Emp>) selAll(Emp.class, 
				"select * from emp where loginname='"+loginname+"' and password='"+password+"'");
		if(empli.size()>0 && comLi.size()>0){		//经销商和业务员的账号密码重复
			result = "{success:true,code:400,msg:'无效的账号和密码。'}";
		} else if(comLi.size()>0){
			//经销商登录
			Company company = comLi.get(0);
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
			result = CommonConst.SUCCESS;
		} else if(empli.size()>0){
			//业务员登录
			Emp emp = empli.get(0);
			Company empCom = ((List<Company>) selAll(Company.class, 
					"select * from company where companyid='"+emp.getEmpcompany()+"'")).get(0);
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
			result = CommonConst.SUCCESS;
		} else {
			result = "{success:true,code:400,msg:'账号或密码不正确。'}";
		}
		responsePW(response, result);
	}
}
