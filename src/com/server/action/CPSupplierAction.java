package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.SupplierPoco;
import com.server.pojo.LoginInfo;
import com.server.pojo.Supplier;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPSupplierAction extends SupplierAction {

	//修改
	public void updSupplier(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Supplier temp:cuss){
			temp.setSupplierupdtime(DateUtils.getDateTime());
			temp.setSupplierupdor(lgif.getUsername());
			result = updSingle(temp,SupplierPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	
	//新增
	public void addSupplier(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Supplier temp:cuss){
			if(CommonUtil.isNull(temp.getSupplierid())){
				temp.setSuppliercretime(DateUtils.getDateTime());
				temp.setSuppliercreor(lgif.getUsername());
				temp.setSupplierid(CommonUtil.getNewId());
			}
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
}
