package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.CcustomerviewPoco;
import com.server.poco.CustomerPoco;
import com.server.poco.OrdermviewPoco;
import com.server.pojo.Ccustomer;
import com.server.pojo.Ccustomerview;
import com.server.pojo.Customer;
import com.server.pojo.LoginInfo;
import com.server.pojo.Ordermview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.BeanToArray;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPCcustomerviewAction extends CcustomerviewAction {

	//客户管理的"修改"
	public void updateCustomerInfo(HttpServletRequest request, HttpServletResponse response){
		LoginInfo loif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()==1){
			String time = DateUtils.getDateTime();
			Ccustomerview t = cuss.get(0);
			//客户
			Customer cus = new Customer();
			cus.setCustomerid(t.getCustomerid());
			cus.setCustomertype(t.getCustomertype());
			cus.setCustomershop(t.getCustomershop());
			cus.setCustomername(t.getCustomername());
			cus.setCustomerphone(t.getCustomerphone());
			cus.setCustomeraddress(t.getCustomeraddress());
			cus.setCustomerstatue(t.getCustomerstatue());
			cus.setCustomercity(t.getCustomercity());
			cus.setCustomerxian(t.getCustomerxian());
			cus.setCustomerlevel(Integer.valueOf(t.getCcustomerdetail()));
			cus.setUpdtime(time);
			//客户关系
			Ccustomer cc = new Ccustomer();
			cc.setCcustomerid(t.getCcustomerid());
			cc.setCcustomerdetail(t.getCcustomerdetail());
			cc.setCreatetime(t.getCreatetime());
			cc.setCcustomerupdtime(time);
			cc.setCcustomerupdor(loif.getUsername());
			String[] cusKey = {"customerid"};
			String[] ccKey = {"ccustomerid"};
			String cusSql = getUpdSingleSql(cus, cusKey);
			String ccSql = getUpdSingleSql(cc, ccKey);
			String[] sqls = {ccSql,cusSql};
			result = doAll(sqls);
		}
		responsePW(response, result);
	}

	//分页查询
	public void queryCCustomerwiew(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Ccustomerview.class, CcustomerviewPoco.QUERYFIELDNAME, CcustomerviewPoco.ORDER, TYPE);
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}

	//customerMana导出
	@SuppressWarnings("unchecked")
	public void expCCustomerwiew(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Ccustomerview.class, CcustomerviewPoco.QUERYFIELDNAME, CcustomerviewPoco.ORDER, TYPE);
		ArrayList<Ccustomerview> list = (ArrayList<Ccustomerview>) selAll(queryinfo);
		String[] heads = {"客户编码","客户姓名","手机","客户经理","店铺","城市","县","街道地址","类型","等级","修改人","修改时间","创建时间"};				//表头
		String[] discard = {"ccustomerid","ccustomercompany","customerid","customerpsw","customerlevel","openid","customerdetail","customerstatue","updtime","creator"};			//要忽略的字段名
		String name = "客户统计报表";							//文件名称
		FileUtil.expExcel(response, list, heads, discard, name);
	}
	
}










