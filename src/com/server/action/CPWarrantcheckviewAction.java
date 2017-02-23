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
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantcheckviewAction extends WarrantcheckviewAction {
	
	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		cuss = (ArrayList<Warrantcheckview>) selAll(Warrantcheckview.class,sql);
		String[] heads = {"商品编号","商品名称","商品规格","仓库","应有数量","现有数量","状态","描述","创建时间","创建人","修改时间","修改人"};
		String[] discard = {"idwarrantcheck","warrantcheckstore","warrantcheckgoods","goodsid","goodscompany" };
		FileUtil.expExcel(response,cuss,heads,discard,"盘点记录");
	}
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
