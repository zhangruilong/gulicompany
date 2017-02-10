package com.server.action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.GoodsPoco;
import com.server.poco.GoodsviewPoco;
import com.server.poco.LargecuspricePoco;
import com.server.pojo.Goods;
import com.server.pojo.Goodsnum;
import com.server.pojo.Goodsview;
import com.server.pojo.LargecuspriceGoodsVO;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.TypeUtil;

/**
 * 商品相关的业务逻辑
 * @author taolichao
 */
public class CPGoodsviewAction extends GoodsviewAction {
	
	//客户录单时的商品和价格
	public void customerGoods(HttpServletRequest request, HttpServletResponse response){
		String companyid = request.getParameter("companyid");
		String pagenowGoods = request.getParameter("pagenowGoods");
		String goodscode = request.getParameter("goodscode");
		String priceslevel = request.getParameter("priceslevel");
		String pricesclass = request.getParameter("pricesclass");
		String customerid = request.getParameter("customerid");
		if(pagenowGoods == null){
			pagenowGoods = "1";
		}
		String sql = "select distinct g.*,p.pricesid,p.pricesprice,p.pricesunit,p.pricesprice2,p.pricesunit2,lcp.largecuspriceprice,gc.goodsclassname"+
			",lcp.largecuspriceunit,lcp.largecuspriceunit2,lcp.largecuspriceprice2,lcp.largecuspriceid ,lcp.largecuspricecreatetime "+
			"from prices p "+
			"left join goods g on p.pricesgoods = g.goodsid "+
			"left join largecusprice lcp on lcp.largecuspricegoods = p.pricesgoods "+
			"left join goodsclass gc on g.goodsclass = gc.goodsclassid "+
		    "where (g.goodsstatue = '上架' or lcp.largecuspriceprice is not null) "+
			"and g.goodscompany = '"+companyid+"' "+
			"and (lcp.largecuspricecustomer = '"+customerid+"' or lcp.largecuspricecustomer is null) "+
			"and ( (p.pricesclass = '"+pricesclass+"' and p.priceslevel = '"+priceslevel+"' ) or "+
					"(p.pricesid is null and lcp.largecuspriceprice is not null) ) "+
			"and (lcp.largecuspricecompany = '"+companyid+"' or lcp.largecuspricecompany is null ) "+
		    "order by lcp.largecuspricecreatetime is null,lcp.largecuspricecreatetime desc ,g.createtime desc";
		String totSql = sql.replace("distinct g.*,p.pricesid,p.pricesprice,p.pricesunit,p.pricesprice2,p.pricesunit2,lcp.largecuspriceprice,gc.goodsclassname"+
			",lcp.largecuspriceunit,lcp.largecuspriceunit2,lcp.largecuspriceprice2,lcp.largecuspriceid ,lcp.largecuspricecreatetime "
			, "count(distinct goodsid) ");
		sql += " limit "+(Integer.parseInt(pagenowGoods)-1)*10+",10";
		@SuppressWarnings("unchecked")
		List<LargecuspriceGoodsVO> voLi = selAll(LargecuspriceGoodsVO.class, sql);
		Integer countGoods = getTotal(totSql);
		Integer pageCountGoods;		//总页数
		if(countGoods % 10 ==0){
			pageCountGoods = countGoods / 10;
		} else {
			pageCountGoods = (countGoods / 10) +1;
		}
		result = "{success:true,code:202,msg:'操作成功',pagenowGoods:"+pagenowGoods+",goodsList:"+CommonConst.GSON.toJson(voLi)+
				",pageCountGoods:"+pageCountGoods+",countGoods:"+countGoods+"}";
		responsePW(response, result);
	}
	//分页查询供应商商品
	@SuppressWarnings("unchecked")
	public void queryCompanyGoods(HttpServletRequest request, HttpServletResponse response){
		String[] curQueryFN = {
			 	"goodscode",
			 	"goodsname",
			 	"goodsunits",
			 	"goodsstatue",
			 	"goodsclassname"
		};	//模糊查询的字段
		//String goodsstatue = request.getParameter("goodsstatue");
		Queryinfo queryinfo = getQueryinfo(request, Goodsview.class, curQueryFN, GoodsviewPoco.ORDER, new TypeToken<ArrayList<Goods>>() {}.getType());
		//String comid = request.getParameter("comid");
		String selectsql = " select g.*,gc.goodsclassname from goods g left join goodsclass gc on g.goodsclass=gc.goodsclassid where 1=1 ";
		if(CommonUtil.isNotEmpty(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(CommonUtil.isNotNull(jsonsql))
				selectsql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getWheresql())){
			selectsql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getQuery())){
			selectsql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getOrder())){
			selectsql += " order by " + queryinfo.getOrder();
		}
		List<Goodsview> goodsLi = selQuery(selectsql, queryinfo);
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), goodsLi);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//删除
	public void deleteGoods(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		Type curType = new TypeToken<ArrayList<Goods>>() {}.getType();
		List<Goods> gLi = new ArrayList<Goods>();
		if(CommonUtil.isNotEmpty(json)) gLi = CommonConst.GSON.fromJson(json, curType);
		for(Goods temp:gLi){
			result = delSingle(temp,GoodsPoco.KEYCOLUMN);
			doSingle("delete from prices where pricesgoods='"+temp.getGoodsid()+"'",null);
		}
		responsePW(response, result);
	}
	//新增
	public void insGoods(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String goodsnum = request.getParameter("goodsnum");
		String goodsnumstore = request.getParameter("goodsnumstore");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		List<Goods> gooLi = new ArrayList<Goods>();
		if(CommonUtil.isNotEmpty(json)) gooLi = CommonConst.GSON.fromJson(json,  new TypeToken<ArrayList<Goods>>() {}.getType());
		if(gooLi.size()>0 && CommonUtil.isNotEmpty(goodsnum) && CommonUtil.isNotEmpty(goodsnumstore)){
			Goods addGoods = gooLi.get(0);
			if(null==addGoods.getGoodsweight()||addGoods.getGoodsweight().equals("")||addGoods.getGoodsweight().equals("undefined")){
				addGoods.setGoodsweight("0");
			}
			if(null==addGoods.getGoodsorder()){
				addGoods.setGoodsorder(0);
			}
			addGoods.setGoodsstatue("下架");
			addGoods.setCreatetime(DateUtils.getDateTime());		//创建时间
			addGoods.setCreator(lInfo.getUsername());			//创建人
			addGoods.setGoodscompany(lInfo.getCompanyid());		//经销商ID
			String newid = CommonUtil.getNewId();
			addGoods.setGoodsid(newid);				//商品ID
			String addGooSql = getInsSingleSql(addGoods);	//新增商品的sql
			Goodsnum gn = new Goodsnum();
			gn.setIdgoodsnum(newid);
			gn.setGoodsnumnum(goodsnum);
			gn.setGoodsnumstore(goodsnumstore);
			gn.setGoodsnumgoods(newid);				//商品ID
			String addGNSql = getInsSingleSql(gn);			//新增库存总账的sql
			String[] sqls = {addGooSql,addGNSql};
			result = doAll(sqls);
			if(result.equals(CommonConst.SUCCESS)){
				result = "{success:true,code:202,msg:'操作成功',goodsid:'"+newid+"'}";
			}
		}
		responsePW(response, result);
	}
}
