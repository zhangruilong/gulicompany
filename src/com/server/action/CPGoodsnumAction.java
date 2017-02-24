package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.pojo.Goods;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPGoodsnumAction extends GoodsnumAction {

	//新增
	@SuppressWarnings("unchecked")
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
			//查询是否有重复的商品
			List<Goods> isRe = selAll(Goods.class, "select * from goods where goodscode='"+addGoods.getGoodscode()+
					"' and goodsunits='"+addGoods.getGoodsunits()+"' and goodsname='"+addGoods.getGoodsname()+
					"' and goodscompany='"+lInfo.getCompanyid()+"'");
			if(isRe.size()==0){
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
				//新增库存总账记录
				Goodsnum gn = new Goodsnum();
				gn.setIdgoodsnum(newid);
				gn.setGoodsnumnum(goodsnum);
				gn.setGoodsnumstore(goodsnumstore);
				gn.setGoodsnumgoods(newid);				//商品ID
				String addGNSql = getInsSingleSql(gn);			//新增库存总账的sql
				String[] sqls = {addGooSql,addGNSql};
				result = doAll(sqls);
			} else {
				Goods goods = isRe.get(0);
				//检查是否有重复的库存总账记录
				List<Goodsnum> reGnLi = selAll(Goodsnum.class, "select * from Goodsnum where goodsnumgoods='"+goods.getGoodsid()+
						"' and goodsnumstore='"+goodsnumstore+"'");
				if(reGnLi.size()==0){
					//新增库存总账记录
					Goodsnum gn = new Goodsnum();
					gn.setIdgoodsnum(CommonUtil.getNewId());
					gn.setGoodsnumnum(goodsnum);
					gn.setGoodsnumstore(goodsnumstore);
					gn.setGoodsnumgoods(goods.getGoodsid());				//商品ID
					result = insSingle(gn);
				} else {
					result = "{success:true,code:400,msg:'当前新增的库存总账记录已存在'}";
				}
			}
		}
		responsePW(response, result);
	}
	//新增
	/*public void insGoodsnum(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Goodsnum temp = cuss.get(0);
			@SuppressWarnings("unchecked")
			List<Goodsnum> reGNLi = selAll(Goodsnum.class, "select * from Goodsnum where goodsnumgoods='"+temp.getGoodsnumgoods()+
					"' and goodsnumstore='"+temp.getGoodsnumstore()+"'");
			if(reGNLi.size()==0){
				temp.setIdgoodsnum(CommonUtil.getNewId());
				result = insSingle(temp);
			} else {
				result = "{success:true,code:400,msg:'仓库和商品重复'}";
			}
		}
		responsePW(response, result);
	}*/
}
