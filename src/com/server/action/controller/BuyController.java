package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.AddressMapper;
import com.server.pojo.entity.Address;

/**
 * 结算
 * @author taolichao
 *
 */
@Controller
public class BuyController {
	@Autowired
	private AddressMapper addressMapper;
	//到结算页面
	@RequestMapping("/guliwang/doBuy")
	public String doBuy(Model model,Address address){
		if(address.getAddressid() == null){
			List<Address> addList = addressMapper.selectDefAddress(address);
			if(addList != null && addList.size() != 0){
				address = addList.get(0);
			} else {
				address = addressMapper.selectByCondition(address.getAddresscustomer()).get(0);
			}
		} else {
			address = addressMapper.selectByPrimaryKey(address.getAddressid());
		}
		model.addAttribute("address", address);
		return "forward:/guliwang/buy.jsp";
	}
	//到结算页面
		@RequestMapping("/guliwangemp/doEmpBuy")
		public String doEmpBuy(Model model,Address address){
			if(address.getAddressid() == null){
				List<Address> addList = addressMapper.selectDefAddress(address);
				if(addList != null && addList.size() != 0){
					address = addList.get(0);
				} else {
					address = addressMapper.selectByCondition(address.getAddresscustomer()).get(0);
				}
			} else {
				address = addressMapper.selectByPrimaryKey(address.getAddressid());
			}
			model.addAttribute("address", address);
			return "forward:/guliwangemp/buy.jsp";
		}
}
