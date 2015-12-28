package com.system.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.System_rolepowerDao;
import com.system.poco.System_rolepowerPoco;
import com.system.pojo.System_rolepower;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;

/**
 * 角色权限 逻辑层
 *@author ZhangRuiLong
 */
public class System_rolepowerAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<System_rolepower> cuss = null;
	public System_rolepowerDao DAO = new System_rolepowerDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<System_rolepower>>() {}.getType();
	
	//将json转换成cuss
	public void json2cuss(HttpServletRequest request){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
	}
	//新增
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(System_rolepower temp:cuss){
			temp.setId(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//从角色模块右侧权限树中分配
	public void insRolepowertree(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		ArrayList<System_rolepower> commonrolepowercuss = new ArrayList<System_rolepower>();
		ArrayList<System_rolepower> commonidrolepowercuss = new ArrayList<System_rolepower>();
		Queryinfo queryinfo = new Queryinfo();
		queryinfo.setType(System_rolepower.class);
		queryinfo.setWheresql(" roleid='" + cuss.get(0).getRoleid() + "'");
		ArrayList<System_rolepower> oldcuss = (ArrayList<System_rolepower>) DAO.selAll(queryinfo);
		for(System_rolepower temp:cuss){
			for(System_rolepower oldtemp:oldcuss){
				if(temp.getPowerid().equals(oldtemp.getPowerid())){
					commonrolepowercuss.add(temp);
					commonidrolepowercuss.add(oldtemp);
				}
			}
		}
		cuss.removeAll(commonrolepowercuss);//等到要新增的rolepower
		for(System_rolepower temp:cuss){
			temp.setId(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		oldcuss.removeAll(commonidrolepowercuss);//等到要删除的rolepower
		for(System_rolepower temp:oldcuss){
			result = DAO.delSingle(temp);
		}
		responsePW(response, result);
	}
	//删除
	public void delAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(System_rolepower temp:cuss){
			result = DAO.delSingle(temp,System_rolepowerPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//修改
	public void updAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		result = DAO.updSingle(cuss.get(0),System_rolepowerPoco.KEYCOLUMN);
		responsePW(response, result);
	}
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepower.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerPoco.ORDER);
		cuss = (ArrayList<System_rolepower>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,System_rolepowerPoco.CHINESENAME,System_rolepowerPoco.KEYCOLUMN,System_rolepowerPoco.NAME);
	}
	//导入
	public void impAll(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,System_rolepowerPoco.NAME,"impAll");
		String json = FileUtil.impExcel(fileinfo.getPath(),System_rolepowerPoco.FIELDNAME); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(System_rolepower temp:cuss){
			temp.setId(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepower.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(System_rolepower.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(System_rolepowerPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
