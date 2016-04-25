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
import com.server.dao.mapper.GivegoodsMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Givegoods;
import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.Orderm;
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
	private OrdermMapper ordermMapper;
	@Autowired
	private GivegoodsMapper givegoodsMapper;
	//查询首页所需的数据
	@RequestMapping(value="/guliwang/doGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		pageInfo.put("cityList", cities);
		pageInfo.put("parentCity", parentCity);
		/*//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '促销商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		//查询到客户的所有秒杀品的订单详情
		List<Orderd> cusOrderdList = null;
		if(customerid != null){
			cusOrderdList = orderdMapper.selectOrderdByCustomerMiaosha(customerid);
		}
		pageInfo.put("cusOrderdList", cusOrderdList);*/
		pageInfo.put("companyCondition", companyCondition);
		return pageInfo;
	}
	//查询首页所需的数据( EMP )
	@RequestMapping(value="/guliwangemp/doEmpGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doEmpGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		pageInfo.put("cityList", cities);
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '秒杀商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		pageInfo.put("companyCondition", companyCondition);
		pageInfo.put("parentCity", parentCity);
		return pageInfo;
	}
	//秒杀页
	@RequestMapping(value="/guliwang/miaoshaPage",produces="application/json")
	@ResponseBody
	public Map<String, Object> miaoshaPage(Company companyCondition){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '秒杀商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		return pageInfo;
	}
	//买赠页
	@RequestMapping(value="/guliwang/maizengPage",produces="application/json")
	@ResponseBody
	public Map<String, Object> maizengPage(Givegoods givegoods){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '买赠商品'
		List<Givegoods> giveList = givegoodsMapper.selectByCompanyXian(givegoods);
		pageInfo.put("giveList", giveList);
		return pageInfo;
	}
	//判断限购数量是否到达上限
	@RequestMapping(value="/guliwang/judgePurchase",produces="application/json")
	@ResponseBody
	public Map<String, Object> judgePurchase(Integer timegoodsnum,String timegoodscode,String customerid){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer num = ordermMapper.selectOrderByCustomerGoods(customerid, timegoodscode);
		if(num != null && num >= timegoodsnum){
			map.put("result", "no");
		} else {
			map.put("result", "ok");
		}
		return map;
	}
}











