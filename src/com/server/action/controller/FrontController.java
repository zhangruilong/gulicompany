package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CityMapper;
import com.server.pojo.City;
/**
 * 首页
 * @author taolichao
 *
 */
@Controller
public class FrontController {
	@Autowired
	private CityMapper cityMapper;				
	
	@RequestMapping("/guliwang/doGuliwangIndex")
	public String doGuliwangIndex(Model model){
		List<City> cities = cityMapper.selectAllCity();		//查询所有地区
		model.addAttribute("cityList", cities);
		
		return "forward:/guliwang/index.jsp";
	}
}
