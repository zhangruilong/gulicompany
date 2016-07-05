package com.server.action.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.Timegoods;

/**
 * 结算
 * @author taolichao
 *
 */
@Controller
public class BuyController {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private TimegoodsMapper timegoodsMapper;
	//到结算页面
	@RequestMapping("/guliwang/doBuy")
	public String doBuy(Model model,Address address){
		if(address == null || address.getAddresscustomer() == null){
			model.addAttribute("nullInfo", "y");
			return "forward:/guliwang/cart.jsp";
		} else if(address.getAddresscustomer() != null && address.getAddressid() == null ){
			List<Address> addList = addressMapper.selectDefAddress(address);
			if(addList != null && addList.size() != 0){
				address = addList.get(0);
			} else {
				address = addressMapper.selectByCondition(address.getAddresscustomer()).get(0);
			}
		} else if(address.getAddressid() != null) {
			address = addressMapper.selectByPrimaryKey(address.getAddressid());
		}
		model.addAttribute("address", address);
		return "forward:/guliwang/buy.jsp";
	}
	
	//检查秒杀商品的剩余数量
	@RequestMapping("/guliwang/checkRestAmount")
	@ResponseBody
	public String checkRestAmount(String timegoodsids,String timegoodssum){
		String[] timegoodsidsStr = timegoodsids.split(",");
		String[] timegoodssumStr = timegoodssum.split(",");
		String msg = "ok";
		for (int i = 0; i < timegoodsidsStr.length; i++) {									//遍历是否有超过秒杀商品剩余数量的
			Timegoods timegoods = timegoodsMapper.selectByPrimaryKey(timegoodsidsStr[i]);
			if(timegoods.getAllnum() != -1 && timegoods.getSurplusnum() - Integer.parseInt(timegoodssumStr[i]) < 0){
				msg = "no";
				break;
			}
		}
		return msg;
	}
	//检查秒杀商品的剩余数量
		@RequestMapping("/guliwang/editRestAmount")
		@ResponseBody
		public String editRestAmount(String timegoodsids,String timegoodssum){
			String[] timegoodsidsStr = timegoodsids.split(",");
			String[] timegoodssumStr = timegoodssum.split(",");
			List<Timegoods> timegoodsList = new ArrayList<Timegoods>();
			for (int i = 0; i < timegoodsidsStr.length; i++) {									//遍历是否有超过秒杀商品剩余数量的
				Timegoods timegoods = timegoodsMapper.selectByPrimaryKey(timegoodsidsStr[i]);
				timegoodsList.add(timegoods);
			}
			for(int i = 0; i < timegoodsList.size() ; i++){
				Timegoods timegoods = timegoodsList.get(i);
				timegoods.setSurplusnum(timegoods.getSurplusnum() - Integer.parseInt(timegoodssumStr[i]));
				timegoodsMapper.updateByPrimaryKeySelective(timegoods);
			}
			return null;
		}
//////////////////////////////////////////////////EMP补单页面  /////////////////////////////////////////////////////////////////////////////////
	
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
	
	//修改秒杀商品的剩余数量
	@RequestMapping("/guliwangemp/editRestAmountEmp")
	@ResponseBody
	public String editRestAmountEmp(String timegoodsids,String timegoodssum){
		String[] timegoodsidsStr = timegoodsids.split(",");
		String[] timegoodssumStr = timegoodssum.split(",");
		String msg = "ok";
		List<Timegoods> timegoodsList = new ArrayList<Timegoods>();
		for (int i = 0; i < timegoodsidsStr.length; i++) {									//遍历是否有超过秒杀商品剩余数量的
			Timegoods timegoods = timegoodsMapper.selectByPrimaryKey(timegoodsidsStr[i]);
			timegoodsList.add(timegoods);
			if(timegoods.getAllnum() != -1 && timegoods.getSurplusnum() - Integer.parseInt(timegoodssumStr[i]) < 0){
				msg = "no";
				break;
			}
		}
		if(msg.equals("ok")){
			for(int i = 0; i < timegoodsList.size() ; i++){
				Timegoods timegoods = timegoodsList.get(i);
				timegoods.setSurplusnum(timegoods.getSurplusnum() - Integer.parseInt(timegoodssumStr[i]));
				timegoodsMapper.updateByPrimaryKeySelective(timegoods);
			}
		}
		return msg;
	}
}
