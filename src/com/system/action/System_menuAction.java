package com.system.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.System_menuDao;
import com.system.pojo.System_user;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;

public class System_menuAction extends BaseAction {
	String result = CommonConst.FAILURE;
	ArrayList<Treeinfo> cuss = null;//改
	System_menuDao DAO = new System_menuDao();//改
	java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<Treeinfo>>() {}.getType();//改
	//查询菜单
	public void selMenuChildren(HttpServletRequest request, HttpServletResponse response){
		System_user user = getCurrentUser(request);
		if(CommonUtil.isNotEmpty(user)){
			String node = request.getParameter("node");
			String json = request.getParameter("json");
			if(null != json && !json.equals("")){
				cuss = CommonConst.GSON.fromJson(json, TYPE);
			}
			String userid = user.getId();
			//if(userid.equals("1")) userid = null;
			if(node.equals("root"))
				result = CommonConst.GSON.toJson(DAO.selMenu("顶级菜单",null,userid),TYPE);
			else
				result = CommonConst.GSON.toJson(DAO.selMenu("%菜单",node,userid),TYPE);
		}
		responsePW(response, result);
	}
	//未被设为快捷菜单的菜单
	public void selMenuRemoveQuick(HttpServletRequest request, HttpServletResponse response){
		System_user user = getCurrentUser(request);
		if(CommonUtil.isNotEmpty(user)){
			String userid = user.getId();
			String query = request.getParameter("query");
			result = CommonConst.GSON.toJson(DAO.selMenuRemoveQuick(query, userid),TYPE);
			result = "{total:0,root:"+result+"}";	
		}
		responsePW(response, result);
	}
}
