package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.LargecuspricePoco;
import com.server.pojo.Largecusprice;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPLargecuspriceviewAction extends LargecuspriceviewAction {

	//特殊商品价格新增
	public void insLCP(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String goodsid = request.getParameter("goodsid");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		List<Largecusprice> lcpLi = new ArrayList<Largecusprice>();
		if(CommonUtil.isNotEmpty(json)) lcpLi = CommonConst.GSON.fromJson(json, new TypeToken<ArrayList<Largecusprice>>() {}.getType());
		for(Largecusprice temp:lcpLi){
			temp.setLargecuspricegoods(goodsid);
			temp.setLargecuspriceid(CommonUtil.getNewId());
			temp.setLargecuspricecompany(lgif.getCompanyid());
			temp.setLargecuspricecreatetime(DateUtils.getDateTime());
			temp.setLargecuspricecreator(lgif.getEmpcode());
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
	//特殊商品价格删除
	public void deleteLCP(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		List<Largecusprice> lcpLi = new ArrayList<Largecusprice>();
		if(CommonUtil.isNotEmpty(json)) lcpLi = CommonConst.GSON.fromJson(json, new TypeToken<ArrayList<Largecusprice>>() {}.getType());
		for(Largecusprice temp:lcpLi){
			result = delSingle(temp,LargecuspricePoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//特殊商品价格修改
	public void updateLCP(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		List<Largecusprice> lcpLi = new ArrayList<Largecusprice>();
		if(CommonUtil.isNotEmpty(json)) lcpLi = CommonConst.GSON.fromJson(json, new TypeToken<ArrayList<Largecusprice>>() {}.getType());
		for(Largecusprice temp:lcpLi){
			temp.setLargecusupdtime(DateUtils.getDateTime());
			temp.setLargecusupdor(lgif.getEmpcode());
			result = updSingle(temp,LargecuspricePoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
}
