package com.server.action.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.AddressMapper;
import com.server.pojo.entity.Address;
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
	//到地址管理页面
	@RequestMapping("/guliwang/doAddressMana")
	public String doAddressMana(Model model,String customerId){
		List<Address> addressList = addressMapper.selectByCondition(customerId);		//根据条件查询地址
		
		model.addAttribute("addressList", addressList);
		
		return "forward:/guliwang/address.jsp";
	}
	//添加新收货地址
	@RequestMapping("/guliwang/addAddress")
	public String addAddress(Model model,Address address,String customerId){
		address.setAddressture(0);
		address.setAddressid(CommonUtil.getNewId());		//设置新id
		addressMapper.insertSelective(address);
		model.addAttribute("address", address);
		model.addAttribute("customerId", customerId);
		return "forward:/guliwang/addAddress.jsp";
	}
	//到修改收货地址页面
	@RequestMapping("/guliwang/doEditAddress")
	public String doEditAddress(Model model,String addressid){
		Address address = addressMapper.selectByPrimaryKey(addressid);
		model.addAttribute("address", address);
		return "forward:/guliwang/editAddress.jsp";
	}
	//修改收货地址
	@RequestMapping("/guliwang/editAddress")
	public String editAddress(Model model,Address address,String customerId){
		
		addressMapper.updateByPrimaryKeySelective(address);
		model.addAttribute("address", address);
		model.addAttribute("customerId", customerId);
		return "forward:/guliwang/editAddress.jsp";
	}
	//删除收货地址
		@RequestMapping("/guliwang/delAddress")
		public String delAddress(Model model,String addressid,String customerId){
			addressMapper.deleteByPrimaryKey(addressid);
			model.addAttribute("addressid", addressid);
			model.addAttribute("customerId", customerId);
			return "forward:/guliwang/editAddress.jsp";
		}
}
