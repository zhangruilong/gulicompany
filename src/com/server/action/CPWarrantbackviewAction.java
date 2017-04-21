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
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantbackviewAction extends WarrantbackviewAction {

	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Warrantbackview.class, WarrantbackviewPoco.QUERYFIELDNAME, WarrantbackviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(!CommonUtil.isNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(!CommonUtil.isNull(startDate) && !CommonUtil.isNull(endDate)){
			sql += " and warrantbackinswhen >='"+startDate+"' and warrantbackinswhen <='"+endDate+"'";
		}
		sql += " order by idwarrantback desc";
		cuss = (ArrayList<Warrantbackview>) selAll(Warrantbackview.class,sql);
		String[] heads = {"商品编号","商品名称","商品规格","仓库","数量","退货人","状态","描述","创建时间","创建人","修改时间","修改人"};
		String[] discard = {"idwarrantback","warrantbackstore","warrantbackgoods","goodsid","goodscompany" };
		FileUtil.expExcel(response,cuss,heads,discard,"退货台账");
	}
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantbackview.class, WarrantbackviewPoco.QUERYFIELDNAME, WarrantbackviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(warrantbacknum) as warrantbacknum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(!CommonUtil.isNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(!CommonUtil.isNull(startDate) && !CommonUtil.isNull(endDate)){
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
