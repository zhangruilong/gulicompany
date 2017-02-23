package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantinviewPoco;
import com.server.pojo.Warrantinview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantinviewAction extends WarrantinviewAction {
	
	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Warrantinview.class, WarrantinviewPoco.QUERYFIELDNAME, WarrantinviewPoco.ORDER, TYPE);
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
			sql += " and warrantininswhen >='"+startDate+"' and warrantininswhen <='"+endDate+"'";
		}
		sql += " order by idwarrantin desc";
		cuss = (ArrayList<Warrantinview>) selAll(Warrantinview.class, sql);
		String[] heads = {"商品编号","商品名称","商品规格","仓库","供货单位","进货价","数量","检验员","状态","备注","创建时间","创建人","修改时间","修改人"};
		String[] discard = {"idwarrantin","warrantinstore","warrantinfrom","warrantingoods","goodsid","goodscompany" };
		FileUtil.expExcel(response,cuss,heads,discard,"入库台账");
	}
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantinview.class, WarrantinviewPoco.QUERYFIELDNAME, WarrantinviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(warrantinprice) as warrantinprice,sum(warrantinnum) as warrantinnum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
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
			sql += " and warrantininswhen >='"+startDate+"' and warrantininswhen <='"+endDate+"'";
		}
		Warrantinview sum = (Warrantinview) selAll(Warrantinview.class,sql).get(0);
		sql += " order by idwarrantin desc";
		String querySQL = sql.replace("select sum(warrantinprice) as warrantinprice,sum(warrantinnum) as warrantinnum", "select *");
		String totalSQL = sql.replace("select sum(warrantinprice) as warrantinprice,sum(warrantinnum) as warrantinnum", "select count(*)");
		cuss = (ArrayList<Warrantinview>) selQuery(querySQL, queryinfo);
		cuss.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
