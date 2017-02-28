package com.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.CcustomerviewPoco;
import com.server.poco.CustomerPoco;
import com.server.poco.OrdermviewPoco;
import com.server.pojo.Address;
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

	//新增
	public void insSpecialCustomer(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)){
			cuss = CommonConst.GSON.fromJson(json, TYPE);
		} 
		if(cuss.size()>0){
			ArrayList<Customer> cusLi = CommonConst.GSON.fromJson(json, new TypeToken<ArrayList<Customer>>() {}.getType());
			Customer cus = cusLi.get(0);
			Ccustomerview temp = cuss.get(0);
			String newid = CommonUtil.getNewId();
			String datatime = DateUtils.getDateTime();
			cus.setCustomerid(newid);
			cus.setOpenid(newid);
			cus.setCreatetime(datatime);	//创建时间
			String insCus = getInsSingleSql(cus);			//新增客户
			//添加新地址
			Address address = new Address();
			address.setAddressture(1);							//自动设为默认地址
			address.setAddressid(newid);		//设置新id
			address.setAddressaddress(temp.getCustomercity()+temp.getCustomerxian()+temp.getCustomeraddress());
			address.setAddresscustomer(newid);				//客户id
			address.setAddressphone(temp.getCustomerphone());
			address.setAddressconnect(temp.getCustomername());
			String insAdd = getInsSingleSql(address);			//新增地址
			//添加与唯一客户的关系
			Ccustomer newccustomer = new Ccustomer();
			newccustomer.setCcustomerid(newid);
			newccustomer.setCcustomercompany(lgif.getCompanyid());
			newccustomer.setCcustomercustomer(newid);
			newccustomer.setCcustomerdetail(temp.getCcustomerdetail().toString());
			newccustomer.setCreator("1");
			String insCC = getInsSingleSql(newccustomer);			//新增客户关系
			String[] sqls = {insCus,insAdd,insCC};
			result = doAll(sqls);
		}
		responsePW(response, result);
	}
	//largeCusXiaDan.jsp页 得到客户信息和地址
	@SuppressWarnings("unchecked")
	public void queryCusAndAddress(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String customerid = request.getParameter("customerid");
		cuss = (ArrayList<Ccustomerview>) selAll(Ccustomerview.class, "select * from ccustomerview where customerid='"+customerid+"' and ccustomercompany='"+lgif.getCompanyid()+"'");
		if(cuss.size()>0){
			List<Address> addLi = selAll(Address.class, "select * from address where addresscustomer='"+customerid+"' and addressture=1");
			if(addLi.size()>0){
				result = "{success:true,code:202,msg:'操作成功',customerInfo:"+CommonConst.GSON.toJson(cuss.get(0))+",address:"+CommonConst.GSON.toJson(addLi.get(0))+"}";
			} else {
				result = "{success:true,code:400,msg:'该客户还没有添加地址'}";
			}
		} else {
			result = "{success:true,code:400,msg:'没有查找到该用户'}";
		}
		responsePW(response, result);
	}
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
		String[] queryName = {
			 	"createtime",
			 	"ccustomerupdtime",
			 	"ccustomerupdor",
			 	"customercode",
			 	"customername",
			 	"customerphone",
			 	"customershop",
			 	"customeraddress",
		   };			//模糊查询的字段名
		Queryinfo queryinfo = getQueryinfo(request, Ccustomerview.class, queryName, CcustomerviewPoco.ORDER, TYPE);
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}

	//customerMana导出
	@SuppressWarnings("unchecked")
	public void expCCustomerwiew(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] queryName = {
			 	"createtime",
			 	"ccustomerupdtime",
			 	"ccustomerupdor",
			 	"customercode",
			 	"customername",
			 	"customerphone",
			 	"customershop",
			 	"customeraddress",
		   };			//模糊查询的字段名
		Queryinfo queryinfo = getQueryinfo(request, Ccustomerview.class, queryName, CcustomerviewPoco.ORDER, TYPE);
		ArrayList<Ccustomerview> list = (ArrayList<Ccustomerview>) selAll(queryinfo);
		String[] heads = {"客户编码","客户姓名","手机","客户经理","店铺","城市","县","街道地址","类型","等级","修改人","修改时间","创建时间"};				//表头
		String[] discard = {"ccustomerid","ccustomercompany","customerid","customerpsw","customerlevel","openid","customerdetail","customerstatue","updtime","creator"};			//要忽略的字段名
		String name = "客户统计报表";							//文件名称
		FileUtil.expExcel(response, list, heads, discard, name);
	}
	
}










