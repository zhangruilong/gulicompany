package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantbackPoco;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantback;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantbackAction extends WarrantbackAction {

	//新增
	public void addWarrantback(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantback temp:cuss){
			if(CommonUtil.isNull(temp.getIdwarrantback())){
				temp.setIdwarrantback(CommonUtil.getNewId());
				temp.setWarrantbackinswhen(DateUtils.getDateTime());
				temp.setWarrantbackinswho(lgi.getUsername());
			}
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
	//修改
	public void updWarrantback(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantback temp:cuss){
			temp.setWarrantbackupdwhen(DateUtils.getDateTime());
			temp.setWarrantbackupdwho(lgi.getUsername());
			result = updSingle(temp,WarrantbackPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
}
