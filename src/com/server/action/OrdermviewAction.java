package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.OrdermviewDao;
import com.server.pojo.Ordermview;
import com.server.poco.OrdermviewPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;
import com.system.tools.pojo.Pageinfo;

/**
 * 订单 逻辑层
 *@author ZhangRuiLong
 */
public class OrdermviewAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<Ordermview> cuss = null;
	public OrdermviewDao DAO = new OrdermviewDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<Ordermview>>() {}.getType();
	
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Ordermview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(OrdermviewPoco.ORDER);
		cuss = (ArrayList<Ordermview>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,OrdermviewPoco.CHINESENAME,OrdermviewPoco.KEYCOLUMN,OrdermviewPoco.NAME);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Ordermview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(OrdermviewPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Ordermview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(OrdermviewPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void mselQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Ordermview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(OrdermviewPoco.ORDER);
		String openid = request.getParameter("openid");
		String begindate = request.getParameter("begindate");
		String enddate = request.getParameter("enddate");
		String beginmoney = request.getParameter("beginmoney");
		String endmoney = request.getParameter("endmoney");
		String companyname = request.getParameter("companyname");
		String wheresql = "openid='"+openid+"'";
		if(CommonUtil.isNotNull(begindate)){
			wheresql += " and ordermtime>'"+begindate+"'";
		}
		if(CommonUtil.isNotNull(enddate)){
			wheresql += " and ordermtime<'"+enddate+"'";
		}
		if(CommonUtil.isNotNull(beginmoney)){
			wheresql += " and ordermmoney>'"+beginmoney+"'";
		}
		if(CommonUtil.isNotNull(endmoney)){
			wheresql += " and ordermmoney<'"+endmoney+"'";
		}
		if(CommonUtil.isNotNull(companyname)){
			wheresql += " and companyshop like '%"+companyname+"%'";
		}
//		if(CommonUtil.isNotNull(wheresql)){
//			wheresql = wheresql.substring(5,wheresql.length());
//		}
		queryinfo.setWheresql(wheresql);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
