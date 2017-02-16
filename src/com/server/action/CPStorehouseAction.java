package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.StorehousePoco;
import com.server.pojo.LoginInfo;
import com.server.pojo.Storehouse;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPStorehouseAction extends StorehouseAction {

	//修改
	public void updStorehouse(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Storehouse temp:cuss){
			temp.setStorehouseupdtime(DateUtils.getDateTime());
			temp.setStorehouseupdor(lgif.getUsername());
			result = updSingle(temp,StorehousePoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//新增仓库
	public void addStorehouse(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Storehouse temp:cuss){
			if(CommonUtil.isNull(temp.getStorehouseid())){
				temp.setStorehousecompany(lgif.getCompanyid());
				temp.setStorehousecretime(DateUtils.getDateTime());
				temp.setStorehousecreor(lgif.getUsername());
				temp.setStorehouseid(CommonUtil.getNewId());
			}
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
}
