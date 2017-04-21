package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumviewPoco;
import com.server.pojo.Goodsnumview;
import com.server.pojo.LargecuspriceGoodsVO;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPGoodsnumviewAction extends GoodsnumviewAction {

	//录手工单页的选择商品
	@SuppressWarnings("unchecked")
	public void manualGoods(HttpServletRequest request, HttpServletResponse response){
		String pagenowGoods = request.getParameter("pagenowGoods");
		Queryinfo queryinfo = getQueryinfo(request, Goodsnumview.class, GoodsnumviewPoco.QUERYFIELDNAME, GoodsnumviewPoco.ORDER, TYPE);
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		sql += " order by goodsid desc";
		String totalSQL = sql.replace("select *", "select count(*)");
		cuss = (ArrayList<Goodsnumview>) selQuery(sql,queryinfo);
		Integer countGoods = getTotal(totalSQL);
		Integer pageCountGoods;		//总页数
		if(countGoods % 10 ==0){
			pageCountGoods = countGoods / 10;
		} else {
			pageCountGoods = (countGoods / 10) +1;
		}
		result = "{success:true,code:202,msg:'操作成功',pagenowGoods:"+pagenowGoods+",goodsList:"+CommonConst.GSON.toJson(cuss)+
				",pageCountGoods:"+pageCountGoods+",countGoods:"+countGoods+"}";
		responsePW(response, result);
	}
	//出库中的选择商品弹窗中的数据
	@SuppressWarnings("unchecked")
	public void selGoodsAndNum(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Goodsnumview.class, GoodsnumviewPoco.QUERYFIELDNAME, GoodsnumviewPoco.ORDER, TYPE);
		String querySQL = "select `gn`.`idgoodsnum`,`gn`.`goodsnumgoods`,`gn`.`goodsnumnum`,`gn`.`goodsnumstore`,`g`.`goodsid`,`g`.`goodscompany`,`g`.`createtime`,`g`.`goodscode`,`g`.`goodsname`,`g`.`goodsunits`,`sh`.`storehousename` from goods g left join goodsnum gn on g.goodsid=gn.goodsnumgoods LEFT JOIN `storehouse` `sh` ON `gn`.`goodsnumstore` = `sh`.`storehouseid` where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			querySQL += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			querySQL += " and (" + queryinfo.getQuery() + ") ";
		}
		String totalSQL = querySQL.replace("`gn`.`idgoodsnum`,`gn`.`goodsnumgoods`,`gn`.`goodsnumnum`,`gn`.`goodsnumstore`,`g`.`goodsid`,`g`.`goodscompany`,`g`.`createtime`,`g`.`goodscode`,`g`.`goodsname`,`g`.`goodsunits`,`sh`.`storehousename`", "count(*)");
		querySQL += " order by goodsid desc";
		cuss = (ArrayList<Goodsnumview>) selQuery(querySQL,queryinfo);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Goodsnumview.class, GoodsnumviewPoco.QUERYFIELDNAME, GoodsnumviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(goodsnumnum) as goodsnumnum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
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
	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Goodsnumview.class, GoodsnumviewPoco.QUERYFIELDNAME, GoodsnumviewPoco.ORDER, TYPE);
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
			sql += " and createtime >='"+startDate+"' and createtime <='"+endDate+"'";
		}
		sql += " order by goodsid desc";
		cuss = (ArrayList<Goodsnumview>) selAll(Goodsnumview.class, sql);
		String[] heads = {"商品编号","商品名称","商品规格","数量","仓库"};
		String[] discard = {"idgoodsnum","goodsnumgoods","goodsid","goodsnumstore","goodscompany","createtime" };
		FileUtil.expExcel(response, cuss, heads, discard, "库存总账");
	}
}
