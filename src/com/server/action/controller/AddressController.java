package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CityMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.City;
import com.system.tools.util.CommonUtil;
/**
 * 地址管理
 * @author taolichao
 *
 */
@Controller
public class AddressController {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CityMapper cityMapper;
	//到地址管理页面
	@RequestMapping("/guliwang/doAddressMana")
	public String doAddressMana(Model model,String customerId){
		List<Address> addressList = addressMapper.selectByCondition(customerId);		//根据条件查询地址
		
		model.addAttribute("addressList", addressList);
		
		return "forward:/guliwang/address.jsp";
	}
	//到添加收货地址页面
	@RequestMapping("/guliwang/doAddAddress")
	public String doAddAddress(Model model){
		List<City> cityList = cityMapper.selectAllCity();
		List<City> cityParents = cityMapper.selectAllParent();
		model.addAttribute("cityList", cityList);
		model.addAttribute("cityParents", cityParents);
		return "forward:/guliwang/addAddress.jsp";
	}
	//添加新收货地址
	@RequestMapping("/guliwang/addAddress")
	public String addAddress(Model model,Address address,String customerId){
		if(address.getAddressture() == 1){					//如果勾选了默认就把其他地址设为不是默认
			Address add = new Address();
			add.setAddresscustomer(customerId);
			add.setAddressture(0);
			addressMapper.updateCusAllAddress(add);
		}
		address.setAddressid(CommonUtil.getNewId());		//设置新id
		addressMapper.insertSelective(address);
		model.addAttribute("address", address);
		model.addAttribute("customerId", customerId);
		return "forward:doAddressMana.action";
	}
	//到修改收货地址页面
	@RequestMapping("/guliwang/doEditAddress")
	public String doEditAddress(Model model,String addressid){
		Address address = addressMapper.selectByPrimaryKey(addressid);
		List<City> cityList = cityMapper.selectAllCity();
		model.addAttribute("cityList", cityList);
		model.addAttribute("address", address);
		return "forward:/guliwang/editAddress.jsp";
	}
	//修改收货地址
	@RequestMapping("/guliwang/editAddress")
	public String editAddress(Model model,Address address,String customerId){
		if(address.getAddressture() == 1){					//如果勾选了默认就把其他地址设为不是默认
			Address add = new Address();
			add.setAddresscustomer(customerId);
			add.setAddressture(0);
			addressMapper.updateCusAllAddress(add);
		}
		addressMapper.updateByPrimaryKeySelective(address);
		model.addAttribute("address", address);
		model.addAttribute("customerId", customerId);
		return "forward:doAddressMana.action";
	}
	//删除收货地址
		@RequestMapping("/guliwang/delAddress")
		public String delAddress(Model model,Address address,String customerId){
			addressMapper.deleteByPrimaryKey(address.getAddressid());
			if(address.getAddressture() == 1){
				//如果删除的是默认地址
				List<Address> addressList = addressMapper.selectByCondition(customerId);		//根据条件查询地址
				Address address2 = addressList.get(0);
				address2.setAddressture(1);
				addressMapper.updateByPrimaryKeySelective(address2);
			}
			model.addAttribute("address", address);
			model.addAttribute("customerId", customerId);
			return "forward:doAddressMana.action";
		}
}
