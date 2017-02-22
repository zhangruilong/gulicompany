package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumviewPoco;
import com.server.pojo.Goodsnumview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.TypeUtil;

public class CPGoodsnumviewAction extends GoodsnumviewAction {

	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Goodsnumview.class, GoodsnumviewPoco.QUERYFIELDNAME, GoodsnumviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(goodsnumnum) as goodsnumnum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
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
			sql += " and createtime >='"+startDate+"' and createtime <='"+endDate+"'";
		}
		Goodsnumview sum = (Goodsnumview) selAll(Goodsnumview.class,sql).get(0);	//统计数据放在一个 对象 里面
		if(null == sum.getGoodsnumnum()){
			sum.setGoodsnumnum("0");
		}
		sql += " order by goodsid desc";
		String querySQL = sql.replace("select sum(goodsnumnum) as goodsnumnum", "select *");
		String totalSQL = sql.replace("select sum(goodsnumnum) as goodsnumnum", "select count(*)");
		cuss = (ArrayList<Goodsnumview>) selQuery(querySQL,queryinfo);
		cuss.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
