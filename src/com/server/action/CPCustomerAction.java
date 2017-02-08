package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.CustomerPoco;
import com.server.pojo.Ccustomer;
import com.server.pojo.Customer;
import com.server.pojo.LoginInfo;
import com.server.pojo.Orderd;
import com.server.pojo.Orderm;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPCustomerAction extends CustomerAction {
	
	//一段时间内有订单的业务员
	public void selectTimeEmpName(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String querySql = "select cc.createtime from ccustomer cc left join orderm om on om.ordermcustomer = cc.ccustomercustomer "+
				"where om.ordermcompany = '"+lgInfo.getCompanyid()+"' and cc.ccustomercompany = '"+lgInfo.getCompanyid()+
				"' and om.ordermstatue != '已删除' and om.ordermtime >= '"+startDate+"' and om.ordermtime <= '"+endDate+
				"' group by cc.createtime";
		Pageinfo pageinfo = new Pageinfo(0, selAll(Ccustomer.class, querySql));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	
	//查询一段时间内有订单的客户
	public void queryOrderCusName(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String query = request.getParameter("query");
		String querySql = "select om.ordermcusshop from orderm om "+
				" where om.ordermcompany = '"+lgInfo.getCompanyid()+
				"' and om.ordermtime >= '"+startDate+"' and om.ordermtime <= '"+endDate+"' and om.ordermstatue != '已删除' ";
		if(lgInfo.getPower().equals("隐藏")){
			querySql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like "
					+ "'%2' or om.ordermid like '%3' or om.ordermid like '%4') ";
		}
		if(CommonUtil.isNotEmpty(query)){
			querySql += "and om.ordermcusshop like '%"+query+"%' ";
		}
		querySql += "group by om.ordermcusshop limit 0,100";
		Pageinfo pageinfo = new Pageinfo(0, selAll(Orderm.class, querySql));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
