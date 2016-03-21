package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
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
	
	@RequestMapping("/guliwang/doGuliwangIndex")
	public String doGuliwangIndex(Model model,Company companyCondition,City parentCity){
		if(companyCondition.getCity().getCityname() == null || companyCondition.getCity().getCityname() == ""){			
			//如果没有登录
			parentCity.setCityname("嘉兴市");							//父类城市名称默认"嘉兴市"
			City city = companyCondition.getCity();
			city.setCityname("海盐县");					//地区名称默认"海盐县"
			companyCondition.setCity(city);
		}
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		model.addAttribute("cityList", cities);
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '促销商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		model.addAttribute("companyList", companyList);
		model.addAttribute("companyCondition", companyCondition);
		model.addAttribute("parentCity", parentCity);
		return "forward:/guliwang/index.jsp";
	}
	
}
