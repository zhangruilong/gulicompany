package com.om.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.om.dao.Om_empgroupDao;
import com.om.dao.Om_groupDao;
import com.om.poco.Om_empgroupPoco;
import com.om.poco.Om_groupPoco;
import com.om.pojo.Om_group;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;

/**
 * 工作组 逻辑层
 *@author ZhangRuiLong
 */
public class Om_groupAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<Om_group> cuss = null;
	public Om_groupDao DAO = new Om_groupDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<Om_group>>() {}.getType();
	
	//将json转换成cuss
	public void json2cuss(HttpServletRequest request){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
	}
	//新增
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(Om_group temp:cuss){
			temp.setGroupid(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//删除
	public void delAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(Om_group temp : cuss){
			result = DAO.delSingle(temp,Om_groupPoco.KEYCOLUMN);
			if(result.equals(CommonConst.SUCCESS)){
				result = new Om_empgroupDao().delSingle(Om_empgroupPoco.TABLE, "groupid='"+temp.getGroupid()+"'");
			}
		}
		responsePW(response, result);
	}
	//修改
	public void updAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		result = DAO.updSingle(cuss.get(0),Om_groupPoco.KEYCOLUMN);
		responsePW(response, result);
	}
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_group.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_groupPoco.ORDER);
		cuss = (ArrayList<Om_group>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,Om_groupPoco.CHINESENAME,Om_groupPoco.KEYCOLUMN,Om_groupPoco.NAME);
	}
	//导入
	public void impAll(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,Om_groupPoco.NAME,"impAll");
		String json = FileUtil.impExcel(fileinfo.getPath(),Om_groupPoco.FIELDNAME); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Om_group temp:cuss){
			temp.setGroupid(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_group.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_groupPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_group.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_groupPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	public void selTree(HttpServletRequest request, HttpServletResponse response){
		String node = request.getParameter("node");
		String wheresql = " parentid='" + node + "'";
		result = CommonConst.GSON.toJson(DAO.selTree(wheresql),TYPE);		
		responsePW(response, result);
	}
}
