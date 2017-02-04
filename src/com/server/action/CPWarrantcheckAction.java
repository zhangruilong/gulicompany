package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantcheckPoco;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantcheck;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantcheckAction extends WarrantcheckAction {

	//新增
	public void insWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantcheck temp:cuss){
			temp.setIdwarrantcheck(CommonUtil.getNewId());
			temp.setWarrantcheckinswhen(DateUtils.getDateTime());	//创建时间
			temp.setWarrantcheckinswho(lgi.getUsername());		//操作人
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
	//修改
	public void updWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantcheck temp:cuss){
			temp.setWarrantcheckupdwhen(DateUtils.getDateTime());//修改时间
			temp.setWarrantcheckupdwho(lgi.getUsername());	//操作人
			result = updSingle(temp,WarrantcheckPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
}
