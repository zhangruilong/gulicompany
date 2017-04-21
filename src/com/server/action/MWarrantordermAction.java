package com.server.action;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.solr.common.SolrDocumentList;

import com.server.pojo.Goodsnum;
import com.server.pojo.Warrantorderm;
import com.server.pojo.Warrantout;
import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantordermPoco;
import com.server.poco.WarrantoutPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseActionDao;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;
import com.system.tools.pojo.Pageinfo;

/**
 * warrantorderm 逻辑层
 *@author ZhangRuiLong
 */
public class MWarrantordermAction extends WarrantordermAction {
	//修改
	public void guozhan(HttpServletRequest request, HttpServletResponse response){
		String newid = CommonUtil.getNewId();
		String nowdate = DateUtils.getDateTime();
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		cuss.get(0).setUpdtime(nowdate);
		String ordermsql = "";
		if(CommonUtil.isNull(cuss.get(0).getOrdermid())){
			cuss.get(0).setOrdermid(newid);
			cuss.get(0).setOrdermcode(newid);
			cuss.get(0).setOrdermtime(nowdate);
			cuss.get(0).setOrdermemp("手工单");
			ordermsql = getInsSingleSql(cuss.get(0));
		}else{
			ordermsql = getUpdSingleSql(cuss.get(0),WarrantordermPoco.KEYCOLUMN);
		}
		ArrayList<String> sqls = new ArrayList<String>();
		sqls.add(ordermsql);
		String goodsnumjson = request.getParameter("goodsnumjson");
		System.out.println("goodsnumjson : " + goodsnumjson);
		Type goodsnumTYPE = new TypeToken<ArrayList<Goodsnum>>() {}.getType();
		ArrayList<Goodsnum> goodsnumcuss = CommonConst.GSON.fromJson(goodsnumjson, goodsnumTYPE);
		for(Goodsnum mGoodsnum:goodsnumcuss){
			String goodsnumsql = "";
			if(CommonUtil.isNull(mGoodsnum.getIdgoodsnum())){
				mGoodsnum.setIdgoodsnum(CommonUtil.getNewId());
				mGoodsnum.setGoodsnumstore("2");
				goodsnumsql = getInsSingleSql(mGoodsnum);
			}else goodsnumsql = getUpdSingleSql(mGoodsnum,GoodsnumPoco.KEYCOLUMN);
			sqls.add(goodsnumsql);
		}
		Type WarrantoutTYPE = new TypeToken<ArrayList<Warrantout>>() {}.getType();
		ArrayList<Warrantout> Warrantoutcuss = CommonConst.GSON.fromJson(goodsnumjson, WarrantoutTYPE);
		for(Warrantout mGoodsnum:Warrantoutcuss){
			String woutsql = "";
			mGoodsnum.setWarrantoutstatue("已发货");
			mGoodsnum.setWarrantoutinswhen(nowdate);
			if(CommonUtil.isNull(mGoodsnum.getIdwarrantout())){
				mGoodsnum.setIdwarrantout(CommonUtil.getNewId());
				mGoodsnum.setWarrantoutodm(cuss.get(0).getOrdermid());
				woutsql = getInsSingleSql(mGoodsnum);
			}else woutsql = getUpdSingleSql(mGoodsnum,WarrantoutPoco.KEYCOLUMN);
			sqls.add(woutsql);
		}
		result = doAll(sqls);
		responsePW(response, result);
	}
}
