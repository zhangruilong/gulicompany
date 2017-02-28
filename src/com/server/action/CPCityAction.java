package com.server.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.CityPoco;
import com.server.pojo.City;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;

public class CPCityAction extends CityAction {

	//查询经销商城市
	/*@SuppressWarnings("unchecked")
	public void selCompanyCity(HttpServletRequest request, HttpServletResponse response){
		
		cuss = (ArrayList<City>) selAll(City.class, "select ct.* from City ct where ct.cityparent=(select cityparent from city where city=)");
		Pageinfo pageinfo = new Pageinfo(0, cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}*/
}
