package com.system.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.System_power_quickviewDao;
import com.system.pojo.System_power_quickview;
import com.system.poco.System_power_quickviewPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;

/**
 * system_power_quickview 逻辑层
 *@author ZhangRuiLong
 */
public class System_power_quickviewAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<System_power_quickview> cuss = null;
	public System_power_quickviewDao DAO = new System_power_quickviewDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<System_power_quickview>>() {}.getType();
	
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_power_quickview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_power_quickviewPoco.ORDER);
		cuss = (ArrayList<System_power_quickview>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,System_power_quickviewPoco.CHINESENAME,System_power_quickviewPoco.KEYCOLUMN,System_power_quickviewPoco.NAME);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_power_quickview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_power_quickviewPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		String userid = getCurrentUser(request).getId();
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_power_quickview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_power_quickviewPoco.ORDER);
		queryinfo.setWheresql("userid='" + userid + "'");
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
