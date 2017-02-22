package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantcheckviewPoco;
import com.server.pojo.Warrantcheckview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantcheckviewAction extends WarrantcheckviewAction {
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantcheckview.class, WarrantcheckviewPoco.QUERYFIELDNAME, WarrantcheckviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(CommonUtil.isNotEmpty(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(CommonUtil.isNotNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(CommonUtil.isNotEmpty(startDate) && CommonUtil.isNotEmpty(endDate)){
			sql += " and warrantcheckinswhen >='"+startDate+"' and warrantcheckinswhen <='"+endDate+"'";
		}
		sql += " order by idwarrantcheck desc";
		String totalSQL = sql.replace("select *", "select count(*)");
		cuss = (ArrayList<Warrantcheckview>) selQuery(sql, queryinfo);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
