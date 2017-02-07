package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class CPGoodsAction extends GoodsAction {

	@SuppressWarnings("unchecked")
	public void dataMigration(HttpServletRequest request, HttpServletResponse response){
		
		List<Address> addressLi = selAll(Address.class, "select * from address", "oracle");
		for (Address g : addressLi) {
			insSingle(g, "mysql");
		}
		List<Bkgoods> bkgoodsLi = selAll(Bkgoods.class, "select * from bkgoods", "oracle");
		for (Bkgoods g : bkgoodsLi) {
			insSingle(g, "mysql");
		}
		List<Ccustomer> ccustomerLi = selAll(Ccustomer.class, "select * from ccustomer", "oracle");
		for (Ccustomer g : ccustomerLi) {
			insSingle(g, "mysql");
		}
		List<City> cityLi = selAll(City.class, "select * from city", "oracle");
		for (City g : cityLi) {
			insSingle(g, "mysql");
		}
		List<Collect> collectLi = selAll(Collect.class, "select * from collect", "oracle");
		for (Collect g : collectLi) {
			insSingle(g, "mysql");
		}
		List<Company> companyLi = selAll(Company.class, "select * from company", "oracle");
		for (Company g : companyLi) {
			insSingle(g, "mysql");
		}
		List<Customer> customerLi = selAll(Customer.class, "select * from customer", "oracle");
		for (Customer g : customerLi) {
			insSingle(g, "mysql");
		}
		List<Emp> empLi = selAll(Emp.class, "select * from emp", "oracle");
		for (Emp g : empLi) {
			insSingle(g, "mysql");
		}
		List<Goods> goodsLi = selAll(Goods.class, "select * from goods", "oracle");
		for (Goods g : goodsLi) {
			insSingle(g, "mysql");
		}
		List<Goodsclass> goodsclassLi = selAll(Goodsclass.class, "select * from goodsclass", "oracle");
		for (Goodsclass g : goodsclassLi) {
			insSingle(g, "mysql");
		}
		List<Indexarea> indexareaLi = selAll(Indexarea.class, "select * from indexarea", "oracle");
		for (Indexarea g : indexareaLi) {
			insSingle(g, "mysql");
		}
		List<Largecusprice> largecuspriceLi = selAll(Largecusprice.class, "select * from largecusprice", "oracle");
		for (Largecusprice g : largecuspriceLi) {
			insSingle(g, "mysql");
		}
		List<Orderd> orderdLi = selAll(Orderd.class, "select * from orderd", "oracle");
		for (Orderd g : orderdLi) {
			insSingle(g, "mysql");
		}
		List<Orderm> ordermLi = selAll(Orderm.class, "select * from orderm", "oracle");
		for (Orderm g : ordermLi) {
			insSingle(g, "mysql");
		}
		List<Prices> pricesLi = selAll(Prices.class, "select * from prices", "oracle");
		for (Prices g : pricesLi) {
			insSingle(g, "mysql");
		}
		List<Scant> scantLi = selAll(Scant.class, "select * from scant", "oracle");
		for (Scant g : scantLi) {
			insSingle(g, "mysql");
		}
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
