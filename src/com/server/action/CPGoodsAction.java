package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsPoco;
import com.server.pojo.Address;
import com.server.pojo.Bkgoods;
import com.server.pojo.Ccustomer;
import com.server.pojo.City;
import com.server.pojo.Collect;
import com.server.pojo.Company;
import com.server.pojo.Customer;
import com.server.pojo.Emp;
import com.server.pojo.Goods;
import com.server.pojo.Goodsclass;
import com.server.pojo.Indexarea;
import com.server.pojo.Largecusprice;
import com.server.pojo.Orderd;
import com.server.pojo.Orderm;
import com.server.pojo.Prices;
import com.server.pojo.Scant;
import com.system.pojo.System_attach;
import com.system.pojo.System_power;
import com.system.pojo.System_role;
import com.system.pojo.System_rolepower;
import com.system.pojo.System_roleuser;
import com.system.pojo.System_temprule;
import com.system.pojo.System_user;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;

public class CPGoodsAction extends GoodsAction {

	//修改商品状态
	public void updGooSta(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Goods temp = cuss.get(0);
			if(temp.getGoodsstatue().equals("上架")){
				Integer pNum = getTotal("select count(1) from prices where pricesgoods='"+temp.getGoodsid()+"'");
				if(pNum>0){
					result = updSingle(temp,GoodsPoco.KEYCOLUMN);
				} else {
					result = "{success:true,code:401,msg:'操作失败'}";
				}
			} else {
				result = updSingle(temp,GoodsPoco.KEYCOLUMN);
			}
		}
		responsePW(response, result);
	}
	//海盐的orderm数据迁移
	@SuppressWarnings("unchecked")
	public void dataMigration2(HttpServletRequest request, HttpServletResponse response){
		List<Address> addressLi = selAll(Address.class, "select t.* from address t left join customer c on t.addresscustomer= c.customerid left join ccustomer cc on c.customerid = cc.ccustomercustomer where cc.ccustomercompany='1'", "oracle");
		for (Address g : addressLi) {
			insSingle(g, "mysql");
		}
		List<Ccustomer> ccustomerLi = selAll(Ccustomer.class, "select * from ccustomer where ccustomercompany='1'", "oracle");
		for (Ccustomer g : ccustomerLi) {
			insSingle(g, "mysql");
		}
		List<Customer> customerLi = selAll(Customer.class, "select c.* from customer c left join ccustomer cc on c.customerid = cc.ccustomercustomer where cc.ccustomercompany='1'", "oracle");
		for (Customer g : customerLi) {
			insSingle(g, "mysql");
		}
		List<Collect> collectLi = selAll(Collect.class, "select co.* from collect co left join ccustomer cc on cc.ccustomercustomer = co.collectcustomer where cc.ccustomercompany='1'", "oracle");
		for (Collect g : collectLi) {
			insSingle(g, "mysql");
		}
		List<Orderm> ordermLi = selAll(Orderm.class, "select * from orderm t where t.ordermcompany='1'", "oracle");
		for (Orderm g : ordermLi) {
			insSingle(g, "mysql");
		}
		List<Orderd> orderdLi = selAll(Orderd.class, "select t.* from orderd t left join orderm om on t.orderdorderm=om.ordermid where om.ordermcompany='1'", "oracle");
		for (Orderd g : orderdLi) {
			insSingle(g, "mysql");
		}
		
		result = CommonConst.SUCCESS;
		responsePW(response, result);
	}
	//数据迁移
	@SuppressWarnings("unchecked")
	public void dataMigration(HttpServletRequest request, HttpServletResponse response){
		
		List<Address> addressLi = selAll(Address.class, "select t.* from address t left join customer c on t.addresscustomer= c.customerid left join ccustomer cc on c.customerid = cc.ccustomercustomer where cc.ccustomercompany='1'", "oracle");
		for (Address g : addressLi) {
			insSingle(g, "mysql");
		}
		List<Bkgoods> bkgoodsLi = selAll(Bkgoods.class, "select * from bkgoods where bkgoodscompany='1'", "oracle");
		for (Bkgoods g : bkgoodsLi) {
			insSingle(g, "mysql");
		}
		List<Ccustomer> ccustomerLi = selAll(Ccustomer.class, "select * from ccustomer where ccustomercompany='1'", "oracle");
		for (Ccustomer g : ccustomerLi) {
			insSingle(g, "mysql");
		}
		/*List<City> cityLi = selAll(City.class, "select * from city", "oracle");
		for (City g : cityLi) {
			insSingle(g, "mysql");
		}*/
		List<Collect> collectLi = selAll(Collect.class, "select co.* from collect co left join ccustomer cc on cc.ccustomercustomer = co.collectcustomer where cc.ccustomercompany='1'", "oracle");
		for (Collect g : collectLi) {
			insSingle(g, "mysql");
		}
		/*List<Company> companyLi = selAll(Company.class, "select * from company where companyid='1'", "oracle");
		for (Company g : companyLi) {
			insSingle(g, "mysql");
		}*/
		List<Customer> customerLi = selAll(Customer.class, "select c.* from customer c left join ccustomer cc on c.customerid = cc.ccustomercustomer where cc.ccustomercompany='1'", "oracle");
		for (Customer g : customerLi) {
			insSingle(g, "mysql");
		}
		/*List<Emp> empLi = selAll(Emp.class, "select * from emp where empcompany='1'", "oracle");
		for (Emp g : empLi) {
			insSingle(g, "mysql");
		}*/
		List<Goods> goodsLi = selAll(Goods.class, "select * from goods where goodscompany='1'", "oracle");
		for (Goods g : goodsLi) {
			insSingle(g, "mysql");
		}
		List<Goodsclass> goodsclassLi = selAll(Goodsclass.class, "select * from goodsclass where goodsclasscompany='1'", "oracle");
		for (Goodsclass g : goodsclassLi) {
			insSingle(g, "mysql");
		}
		/*List<Indexarea> indexareaLi = selAll(Indexarea.class, "select * from indexarea where indexareacompany='1'", "oracle");
		for (Indexarea g : indexareaLi) {
			insSingle(g, "mysql");
		}*/
		List<Largecusprice> largecuspriceLi = selAll(Largecusprice.class, "select * from largecusprice where largecuspricecompany='1'", "oracle");
		for (Largecusprice g : largecuspriceLi) {
			insSingle(g, "mysql");
		}
		List<Orderd> orderdLi = selAll(Orderd.class, "select t.* from orderd t left join orderm om on t.orderdorderm=om.ordermid where om.ordermcompany='1'", "oracle");
		for (Orderd g : orderdLi) {
			insSingle(g, "mysql");
		}
		List<Orderm> ordermLi = selAll(Orderm.class, "select * from orderm t where t.ordermcompany='1'", "oracle");
		for (Orderm g : ordermLi) {
			insSingle(g, "mysql");
		}
		List<Prices> pricesLi = selAll(Prices.class, "select p.* from prices p left join goods g on p.pricesgoods=g.goodsid where g.goodscompany='1'", "oracle");
		for (Prices g : pricesLi) {
			insSingle(g, "mysql");
		}
		/*List<Scant> scantLi = selAll(Scant.class, "select * from scant", "oracle");
		for (Scant g : scantLi) {
			insSingle(g, "mysql");
		}*/
		List<System_attach> system_attachLi = selAll(System_attach.class, "select * from system_attach", "oracle");
		for (System_attach g : system_attachLi) {
			insSingle(g, "mysql");
		}
		List<System_power> system_powerLi = selAll(System_power.class, "select * from system_power", "oracle");
		for (System_power g : system_powerLi) {
			insSingle(g, "mysql");
		}
		List<System_role> system_roleLi = selAll(System_role.class, "select * from system_role", "oracle");
		for (System_role g : system_roleLi) {
			insSingle(g, "mysql");
		}
		List<System_rolepower> system_rolepowerLi = selAll(System_rolepower.class, "select * from system_rolepower", "oracle");
		for (System_rolepower g : system_rolepowerLi) {
			insSingle(g, "mysql");
		}
		List<System_roleuser> system_roleuserLi = selAll(System_roleuser.class, "select * from system_roleuser", "oracle");
		for (System_roleuser g : system_roleuserLi) {
			insSingle(g, "mysql");
		}
		List<System_temprule> system_tempruleLi = selAll(System_temprule.class, "select * from system_temprule", "oracle");
		for (System_temprule g : system_tempruleLi) {
			insSingle(g, "mysql");
		}
		List<System_user> system_userLi = selAll(System_user.class, "select * from system_user", "oracle");
		for (System_user g : system_userLi) {
			insSingle(g, "mysql");
		}
		result = CommonConst.SUCCESS;
		responsePW(response, result);
	}
	
	public void columnName(HttpServletRequest request, HttpServletResponse response){
		responsePW(response, result);
	}
}
