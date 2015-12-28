package com.system.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.System_powerDao;
import com.system.dao.System_rolepowerviewDao;
import com.system.pojo.System_rolepowerview;
import com.system.poco.System_rolepowerviewPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;

/**
 * system_rolepowerview 逻辑层
 *@author ZhangRuiLong
 */
public class System_rolepowerviewAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<System_rolepowerview> cuss = null;
	public System_rolepowerviewDao DAO = new System_rolepowerviewDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<System_rolepowerview>>() {}.getType();
	
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepowerview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerviewPoco.ORDER);
		cuss = (ArrayList<System_rolepowerview>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,System_rolepowerviewPoco.CHINESENAME,System_rolepowerviewPoco.KEYCOLUMN,System_rolepowerviewPoco.NAME);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepowerview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerviewPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepowerview.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerviewPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//根据roleid查询所有的power
	public void selRolepowertree(HttpServletRequest request, HttpServletResponse response){
		String roleid = request.getParameter("roleid");
		Queryinfo queryinfo = new Queryinfo();
		queryinfo.setType(System_rolepowerview.class);
		queryinfo.setWheresql(" roleid='" + roleid + "'");
		ArrayList<System_rolepowerview> roleidcuss = (ArrayList<System_rolepowerview>) DAO.selAll(queryinfo);
		String powerwheresql = " menulevel='顶级菜单'";
		ArrayList<Treeinfo> parentTreeinfo = new System_powerDao().selPowertree(powerwheresql);
		parentTreeinfo = addchild(parentTreeinfo,roleidcuss);
		result = CommonConst.GSON.toJson(parentTreeinfo,TYPE);	
		responsePW(response, result);
	}
	//寻找子节点
	private ArrayList<Treeinfo> addchild(ArrayList<Treeinfo> parentTreeinfo,ArrayList<System_rolepowerview> roleidcuss) {
		for(int i=0;i<parentTreeinfo.size();i++){
			String powerid = parentTreeinfo.get(i).getId();
			Boolean havepower = checkrolepower(powerid,roleidcuss);
			parentTreeinfo.get(i).setChecked(havepower);
			String powerwheresql = " parentid='" + powerid + "'";
			ArrayList<Treeinfo> childTreeinfo = new System_powerDao().selPowertree(powerwheresql);
			if(CommonUtil.isNotEmpty(childTreeinfo)){
				childTreeinfo = addchild(childTreeinfo,roleidcuss);
			}
			parentTreeinfo.get(i).setChildren(childTreeinfo);
		}
		return parentTreeinfo;
	}
	//判断是否有该权限
	private Boolean checkrolepower(String powerid,ArrayList<System_rolepowerview> roleidcuss) {
		for(System_rolepowerview temp:roleidcuss){
			if(powerid.equals(temp.getPowerid())){
				return true;
			}
		}
		return false;
	}
}
