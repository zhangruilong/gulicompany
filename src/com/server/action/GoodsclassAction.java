package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.GoodsclassDao;
import com.server.pojo.Goodsclass;
import com.server.poco.GoodsclassPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;
import com.system.tools.pojo.Pageinfo;

/**
 * 大小类 逻辑层
 *@author ZhangRuiLong
 */
public class GoodsclassAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<Goodsclass> cuss = null;
	public GoodsclassDao DAO = new GoodsclassDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<Goodsclass>>() {}.getType();
	
	//将json转换成cuss
	public void json2cuss(HttpServletRequest request){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
	}
	//新增
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(Goodsclass temp:cuss){
			temp.setGoodsclassid(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//删除
	public void delAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(Goodsclass temp:cuss){
			result = DAO.delSingle(temp,GoodsclassPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//修改
	public void updAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		result = DAO.updSingle(cuss.get(0),GoodsclassPoco.KEYCOLUMN);
		responsePW(response, result);
	}
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Goodsclass.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(GoodsclassPoco.ORDER);
		cuss = (ArrayList<Goodsclass>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,GoodsclassPoco.CHINESENAME,GoodsclassPoco.KEYCOLUMN,GoodsclassPoco.NAME);
	}
	//导入
	public void impAll(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,GoodsclassPoco.NAME,"impAll");
		String json = FileUtil.impExcel(fileinfo.getPath(),GoodsclassPoco.FIELDNAME); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Goodsclass temp:cuss){
			temp.setGoodsclassid(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Goodsclass.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(GoodsclassPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Goodsclass.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(GoodsclassPoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//获取权限树
	public void selTree(HttpServletRequest request, HttpServletResponse response){
		String node = request.getParameter("node");
		if(node.equals("root")){
			String wheresql = " goodsclassparent='root'";
			result = CommonConst.GSON.toJson(DAO.selTree(wheresql),TYPE);
		}else{
			String wheresql = " goodsclassparent='" + node + "'";
			result = CommonConst.GSON.toJson(DAO.selTree(wheresql),TYPE);		
		}
		responsePW(response, result);
	}
}
