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
import com.server.dao.mapper.EmpMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.dao.mapper.System_tempruleMapper;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Emp;
import com.server.pojo.entity.LoginInfo;
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
	@Autowired
	private EmpMapper empMapper;
	
	//修改供应商信息
	@RequestMapping("/companySys/editCompInfo")
	@ResponseBody
	public HashMap<String, Object> editCompInfo(HttpSession session,Company company){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		LoginInfo lif = (LoginInfo) session.getAttribute("loginInfo");
		int result = 0;
		if(lif.getPower().equals("company")){
			result = companyMapper.updateByPrimaryKeySelective(company);
		}
		if(result>0){
			lif.setComusername(company.getUsername());
			lif.setCompanyshop(company.getCompanyshop());
			lif.setCompanyphone(company.getCompanyphone());
			session.setAttribute("loginInfo", lif);
			resultMap.put("msg", "修改成功!");
		} else {
			resultMap.put("msg", "操作失败!");
		}
		return resultMap;
	}
	//修改密码
	@RequestMapping("/companySys/editPwd")
	public String editPwd(HttpSession session,Model model,String loginname,String password,String newpassword){
		Company company = null;
		List<Emp> empli = null;
		if(password != null && password != ""){
			company = companyMapper.selectLogin(loginname,password);
			empli = empMapper.selectEmpLogin(loginname, password);
		}
		if(null != company){
			company.setPassword(newpassword);
			companyMapper.updateByPrimaryKeySelective(company);
			session.invalidate();
			model.addAttribute("message", "密码已修改");
		} else if(null != empli && empli.size()>0){
			Emp emp = empli.get(0);
			emp.setPassword(newpassword);
			empMapper.updateByPrimaryKeySelective(emp);
			session.invalidate();
			model.addAttribute("message", "密码已修改");
		} else {
			model.addAttribute("message", "您输入的密码不正确，请重新输入。");
		}
		return "forward:/companySys/editPas.jsp";
	}
	//打印订单
	@RequestMapping("/companySys/printInfo")
	@ResponseBody
	public Map<String, Object> printInfo(String ordermids){
		Map<String, Object> result = new HashMap<String, Object>();
		String[] odmids = ordermids.split(",");
		List<PrintInfo> piLi = ordermMapper.selectPrintInfo(odmids);		//查询 订单、客户、经销商 信息
		if(null!=piLi && piLi.size()>0){
			List<System_temprule> tempList = sys_tempruleMapper.selectByComid(piLi.get(0).getOrdermcompany());	//查询模板
			List<Orderd> odLI = orderdMapper.selectByOrderm(odmids);		//一个或多个订单中的订单商品
			List<Orderd> reOds = orderdMapper.selectRepeatOrderd(odmids);	//查询重复的订单商品信息(编号/类型/规格/价格)
			if(null != reOds && reOds.size()>0){
				for (Orderd reOd : reOds) {
					Orderd newOd = null;									//合并后的订单商品
					ArrayList<Orderd> removeOdLi = new ArrayList<Orderd>();		//要移除的订单商品
					for (Orderd od : odLI) {
						if(od.getOrderdcode().equals(reOd.getOrderdcode()) && od.getOrderdtype().equals(reOd.getOrderdtype()) &&
								od.getOrderdunits().equals(reOd.getOrderdunits()) && od.getOrderdprice().equals(reOd.getOrderdprice())){
							if(null == newOd){
								newOd = od;
							} else {
								newOd.setOrderdnum(newOd.getOrderdnum() + od.getOrderdnum());
								newOd.setOrderdmoney(String.valueOf(								//下单金额累加
										Float.parseFloat(newOd.getOrderdmoney()) + Float.parseFloat(od.getOrderdmoney())));
								newOd.setOrderdrightmoney(String.valueOf(							//实际金额累加
										Float.parseFloat(newOd.getOrderdrightmoney()) + Float.parseFloat(od.getOrderdrightmoney())));
							}
							removeOdLi.add(od);
						}
					}
					odLI.removeAll(removeOdLi);			//移除掉重复的订单商品
					odLI.add(newOd);					//添加合并后的订单商品
				}
			}
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











