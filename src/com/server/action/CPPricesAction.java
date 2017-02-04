package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsPoco;
import com.server.poco.PricesPoco;
import com.server.pojo.Goods;
import com.server.pojo.LoginInfo;
import com.server.pojo.Prices;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPPricesAction extends PricesAction {

	//设置商品的价格
	public void setGoodsPrices(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String isAct = request.getParameter("isAct");		//是否上架
		String isAdd = request.getParameter("isAdd");		//是否是新增
		String priceScope = request.getParameter("priceScope");		//客户范围
		String time = DateUtils.getDateTime();
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		ArrayList<String> sqls = new ArrayList<String>();
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0 && CommonUtil.isNotEmpty(priceScope) && CommonUtil.isNotEmpty(isAdd)){
			String[] priceStatue = priceScope.split(",");
			
			if(isAdd.equals("true")){
				//新增价格
				for(int i = 0; i < cuss.size(); i++){
					Prices temp = cuss.get(i);
					temp.setPricesid(CommonUtil.getNewId());
					if(priceStatue[i/3].equals("true")){
						temp.setCreator("启用");					//价格状态设置为启用
					} else if (priceStatue[i/3].equals("false")){
						temp.setCreator("禁用");					//价格状态设置为禁用
					}
					if(i < 3){
						temp.setPricesclass("3");				//分类
					} else if(i>=3 && i<6){
						temp.setPricesclass("2");
					} else if(i>=6 && i<9){
						temp.setPricesclass("1");
					}
					temp.setPriceslevel(3-(i%3));						//等级
					temp.setCreatetime(time);		//创建时间
					String insPriSQL = getInsSingleSql(temp);
					sqls.add(insPriSQL);
				}
			} else {
				//修改价格
				for(int i = 0; i < cuss.size(); i++){
					Prices temp = cuss.get(i);
					if(priceStatue[i/3].equals("true")){
						temp.setCreator("启用");					//价格状态设置为启用
					} else if (priceStatue[i/3].equals("false")){
						temp.setCreator("禁用");					//价格状态设置为禁用
					}
					temp.setUpdtime(time);						//修改时间
					temp.setUpdor(lInfo.getUsername());						//修改人
					String updPriSQL = getUpdSingleSql(temp, PricesPoco.KEYCOLUMN);
					sqls.add(updPriSQL);
				}
			}
			Goods goods = new Goods();
			goods.setGoodsid(cuss.get(0).getPricesgoods());
			goods.setUpdtime(time);
			goods.setUpdor(lInfo.getUsername());
			if(isAct.equals("y")){
				goods.setGoodsstatue("上架");
			}
			String updGooSQL = getUpdSingleSql(goods, GoodsPoco.KEYCOLUMN);
			sqls.add(updGooSQL);
			String[] sqlArr = sqls.toArray(new String[sqls.size()]);
			result = doAll(sqlArr, "mysql");
		}
		responsePW(response, result);
	}
}
