package com.server.action.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Company;
/**
 * 首页
 * @author taolichao
 *
 */
@Controller
public class FrontController {
	@Autowired
	private CityMapper cityMapper;	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	@RequestMapping(value="/guliwang/doGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		pageInfo.put("cityList", cities);
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '促销商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		pageInfo.put("companyCondition", companyCondition);
		pageInfo.put("parentCity", parentCity);
		return pageInfo;
	}
	@RequestMapping(value="/guliwangemp/doEmpGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doEmpGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		pageInfo.put("cityList", cities);
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '促销商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		pageInfo.put("companyCondition", companyCondition);
		pageInfo.put("parentCity", parentCity);
		return pageInfo;
	}
	@RequestMapping(value="/guliwangemp/judgePurchase",produces="application/json")
	@ResponseBody
	public Map<String, Object> judgePurchase(Integer timegoodsnum,String timegoodscode){
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}











