package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.pojo.Ccustomer;
import com.server.pojo.City;
import com.server.pojo.Company;
import com.server.pojo.Customer;
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
	private CcustomerMapper ccustomerMapper;
	
	@RequestMapping("/guliwang/doGuliwangIndex")
	public String doGuliwangIndex(Model model,Company companyCondition){
		List<City> cities = cityMapper.selectAllCity();		//查询所有地区
		model.addAttribute("cityList", cities);
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '促销商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		model.addAttribute("companyList", companyList);
		return "forward:/guliwang/index.jsp";
	}
}
