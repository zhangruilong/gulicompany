package com.server.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.Prices;

public class TestApp {
	
	@Test
	public void testMappers(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		AddressMapper mapper = (AddressMapper) context.getBean("addressMapper");
		Address a = new Address();
		a.setAddresscustomer("1");
		a.setAddressture(1);
		List<Address> addList = mapper.selectDefAddress(a);
		System.out.println(addList);
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
	@Test
	public void testgetGoodsPrices(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		GoodsMapper mapper = (GoodsMapper) context.getBean("goodsMapper");
		Goods goods = mapper.selectByPrimaryKey("2");
		List<Prices> pricesList = goods.getPricesList();
		for (Prices prices : pricesList) {
			System.out.println(prices.getPricesid());
		}
	}
	@Test
	public void testParam(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		OrdermMapper mapper = (OrdermMapper) context.getBean("ordermMapper");
		Orderm orderm = new Orderm();
		orderm.setOrdermcompany("1");
		List<Orderm> list = mapper.selectByTime("2016-03-16 00:00:00", "2016-03-19 00:00:00", orderm);
		System.out.println(list);
	}
}
