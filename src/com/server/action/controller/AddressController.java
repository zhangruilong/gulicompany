package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CityMapper;
import com.server.pojo.Address;
import com.server.pojo.City;
/**
 * 地址管理
 * @author taolichao
 *
 */
@Controller
public class AddressController {

	@Autowired
	private AddressMapper addressMapper;
	//到地址管理页面fff
	@RequestMapping("/guliwang/doAddressMana")
	public String doAddressMana(Model model,Address address){
		List<Address> addressList = addressMapper.selectByCondition(address);		//根据条件查询地址
		
		model.addAttribute("addressList", addressList);
		
		return "forward:/guliwang/address.jsp";
	}
}
