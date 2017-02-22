package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantbackviewPoco;
import com.server.pojo.Warrantbackview;
import com.server.pojo.Warrantoutview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantbackviewAction extends WarrantbackviewAction {

	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantbackview.class, WarrantbackviewPoco.QUERYFIELDNAME, WarrantbackviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(warrantbacknum) as warrantbacknum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
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
			sql += " and warrantbackinswhen >='"+startDate+"' and warrantbackinswhen <='"+endDate+"'";
		}
		/*Warrantbackview sum = (Warrantbackview) selAll(Warrantbackview.class,sql).get(0);		//这里是查询统计数据
		if(null == sum.getWarrantbacknum()){
			sum.setWarrantbacknum("0");
		}*/
		sql += " order by idwarrantback desc";
		String querySQL = sql.replace("select sum(warrantbacknum) as warrantbacknum", "select *");
		String totalSQL = sql.replace("select sum(warrantbacknum) as warrantbacknum", "select count(*)");
		cuss = (ArrayList<Warrantbackview>) selQuery(querySQL, queryinfo);
//		cuss.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
