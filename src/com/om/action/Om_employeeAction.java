package com.om.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.om.dao.Om_employeeDao;
import com.om.dao.Om_emporgDao;
import com.om.dao.Om_emppositionDao;
import com.om.poco.Om_employeePoco;
import com.om.pojo.Om_employee;
import com.om.pojo.Om_emporg;
import com.om.pojo.Om_empposition;
import com.system.dao.System_userDao;
import com.system.pojo.System_user;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseAction;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CipherUtil;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.FileUtil;

/**
 * 人员 逻辑层
 *@author ZhangRuiLong
 */
public class Om_employeeAction extends BaseAction {
	public String result = CommonConst.FAILURE;
	public ArrayList<Om_employee> cuss = null;
	public Om_employeeDao DAO = new Om_employeeDao();
	public java.lang.reflect.Type TYPE = new com.google.gson.reflect.TypeToken<ArrayList<Om_employee>>() {}.getType();
	
	//将json转换成cuss
	public void json2cuss(HttpServletRequest request){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
	}
	//新增
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		Om_employee employee = cuss.get(0);
		String empid = CommonUtil.getNewId();
		employee.setEmpid(empid);
		result = DAO.insSingle(employee);
		if(result.equals(CommonConst.SUCCESS)){
			Om_emporg emporg = new Om_emporg(empid,employee.getOrgid(), empid, "是");
			result = new Om_emporgDao().insSingle(emporg);
			if(result.equals(CommonConst.SUCCESS)){
				Om_empposition empposition = new Om_empposition(empid,employee.getPosition(), empid, "是");
				result = new Om_emppositionDao().insSingle(empposition);
				String loginname = employee.getLoginname();
				if(CommonUtil.isNotEmpty(employee.getLoginname())&&result.equals(CommonConst.SUCCESS)){
					String pwd = CipherUtil.generatePassword("1");
					System_user user = new System_user(empid, loginname, pwd, employee.getEmpname(), "启用");
					result = new System_userDao().insSingle(user);
				}
			}
		}
		responsePW(response, result);
	}
	//删除
	public void delAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		for(Om_employee temp:cuss){
			result = DAO.delSingle(temp,Om_employeePoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//修改
	public void updAll(HttpServletRequest request, HttpServletResponse response){
		json2cuss(request);
		result = DAO.updSingle(cuss.get(0),Om_employeePoco.KEYCOLUMN);
		responsePW(response, result);
	}
	//导出
	public void expAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_employee.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_employeePoco.ORDER);
		cuss = (ArrayList<Om_employee>) DAO.selAll(queryinfo);
		FileUtil.expExcel(response,cuss,Om_employeePoco.CHINESENAME,Om_employeePoco.KEYCOLUMN,Om_employeePoco.NAME);
	}
	//导入
	public void impAll(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,Om_employeePoco.NAME,"impAll");
		String json = FileUtil.impExcel(fileinfo.getPath(),Om_employeePoco.FIELDNAME); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Om_employee temp:cuss){
			temp.setEmpid(CommonUtil.getNewId());
			result = DAO.insSingle(temp);
		}
		responsePW(response, result);
	}
	//查询所有
	public void selAll(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_employee.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_employeePoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(0, DAO.selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//分页查询
	public void selQuery(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		queryinfo.setType(Om_employee.class);
		queryinfo.setQuery(DAO.getQuerysql(queryinfo.getQuery()));
		queryinfo.setOrder(Om_employeePoco.ORDER);
		Pageinfo pageinfo = new Pageinfo(DAO.getTotal(queryinfo), DAO.selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
