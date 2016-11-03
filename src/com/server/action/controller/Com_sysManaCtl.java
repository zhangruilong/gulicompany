package com.server.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.dao.mapper.System_tempruleMapper;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.PrintInfo;
import com.server.pojo.entity.System_temprule;

/**
 * 供应商后台管理系统-系统管理
 * @author tao
 *
 */
@Controller
public class Com_sysManaCtl {
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private OrdermMapper ordermMapper;
	@Autowired
	private OrderdMapper orderdMapper;
	@Autowired
	private System_tempruleMapper sys_tempruleMapper;
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
		if(company.getPassword() != null && company.getPassword() != ""){
			company = companyMapper.selectLogin(company);
		} else {
			company = null;
		}
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
	//打印订单
	@RequestMapping("/companySys/printInfo")
	@ResponseBody
	public Map<String, Object> printInfo(String ordermids){
		Map<String, Object> result = new HashMap<String, Object>();
		String[] odmids = ordermids.split(",");
		List<PrintInfo> piLi = ordermMapper.selectPrintInfo(odmids);
		if(null!=piLi && piLi.size()>0){
			List<System_temprule> tempList = sys_tempruleMapper.selectByComid(piLi.get(0).getOrdermcompany());
			List<Orderd> odLI = orderdMapper.selectByOrderm(odmids);
			result.put("tempList", tempList);
			result.put("printinfo", piLi);
			result.put("orderdList", odLI);
			result.put("message", "success");
		} else {
			result.put("message", "error");
		}
		return result;
	}
}











