package com.server.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.Prices;

public class TestApp {
	@Test
	public void testsetgoodsid(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		OrderdMapper odmapper = (OrderdMapper) context.getBean("orderdMapper");
		GoodsMapper gmapper = (GoodsMapper) context.getBean("goodsMapper");
		List<Orderd> odLi = odmapper.selectAllOrderd();
		for (int i = 0; i < odLi.size(); i++) {
			Orderd od = odLi.get(i);
			if(null!=od && null != od.getOrderm()){
				Goods gd = gmapper.selectByGoodsCode(
						od.getOrderdcode(), 
						od.getOrderm().getOrdermcompany(), 
						od.getOrderdunits());
				if(null != gd){
					od.setOrderdgoods(gd.getGoodsid());
					odmapper.updateByPrimaryKeySelective(od);
				}
			}
		}
	}
	
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
}
