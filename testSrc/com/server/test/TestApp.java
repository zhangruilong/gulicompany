package com.server.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.City;
import com.server.pojo.Company;
import com.server.pojo.Customer;
import com.server.pojo.Goods;

public class TestApp {
	
	@Test
	public void testMappers(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		CityMapper mapper = (CityMapper) context.getBean("cityMapper");
		City city = new City();
		city.setCityparent("上海市");
		List<City> cityList = mapper.selectByCityparent(city);
		System.out.println(cityList);
	}
	
	@Test
	public void testResultMap(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		CustomerMapper mapper = (CustomerMapper) context.getBean("customerMapper");
		Customer customer = mapper.selectCollectGoodsById("1");
		System.out.println(customer);
	}
	
	@Test
	public void testFindByCondition(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		CompanyMapper mapper = (CompanyMapper) context.getBean("companyMapper");
		Company company = new Company();
		City city = new City();
		city.setCityname("静安区");
		company.setCity(city);
		List<Company> list = mapper.selectCompanyByCondition(company);
		System.out.println(list);
	}
}
