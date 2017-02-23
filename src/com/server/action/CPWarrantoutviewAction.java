package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantoutviewPoco;
import com.server.pojo.Warrantinview;
import com.server.pojo.Warrantoutview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantoutviewAction extends WarrantoutviewAction {

	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Warrantoutview.class, WarrantoutviewPoco.QUERYFIELDNAME, WarrantoutviewPoco.ORDER, TYPE);
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
			sql += " and warrantoutinswhen >='"+startDate+"' and warrantoutinswhen <='"+endDate+"'";
		}
		Warrantoutview sum = (Warrantoutview) selAll(Warrantoutview.class,sql).get(0);
		if(null == sum.getWarrantoutnum()){
			sum.setWarrantoutnum("0");
		}
		sql += " order by idwarrantout desc";
		cuss = (ArrayList<Warrantoutview>) selAll(Warrantoutview.class, sql);
		String[] heads = {"商品编号","商品名称","商品规格","仓库","数量","状态","备注","领货人","创建时间","创建人","修改时间","修改人"};
		String[] discard = {"idwarrantout","warrantoutstore","warrantoutgoods","goodsid","goodscompany" };
		FileUtil.expExcel(response,cuss,heads,discard,"出库台账");
	}
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantoutview.class, WarrantoutviewPoco.QUERYFIELDNAME, WarrantoutviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(warrantoutnum) as warrantoutnum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
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
			sql += " and warrantoutinswhen >='"+startDate+"' and warrantoutinswhen <='"+endDate+"'";
		}
		Warrantoutview sum = (Warrantoutview) selAll(Warrantoutview.class,sql).get(0);
		if(null == sum.getWarrantoutnum()){
			sum.setWarrantoutnum("0");
		}
		sql += " order by idwarrantout desc";
		String querySQL = sql.replace("select sum(warrantoutnum) as warrantoutnum", "select *");
		String totalSQL = sql.replace("select sum(warrantoutnum) as warrantoutnum", "select count(*)");
		cuss = (ArrayList<Warrantoutview>) selQuery(querySQL, queryinfo);
		cuss.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
