package com.server.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.server.dao.mapper.CityMapper;
import com.server.pojo.City;

public class TestApp {
	
	@Test
	public void testMappers(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		CityMapper mapper = (CityMapper) context.getBean("cityMapper");
		List<City> city = mapper.selectAllCity();
		System.out.println(city);
	}
}
