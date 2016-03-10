package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.OrdermMapper;
import com.server.pojo.entity.Orderm;

/**
 * 供应商后台管理系统-订单管理
 * @author tao
 *
 */
@Controller
public class Com_orderCtl {
	@Autowired
	private OrdermMapper ordermMapper;
	//全部订单
	@RequestMapping("/companySys/allOrder")
	public String allOrder(Model model,Orderm order){
		List<Orderm> ordermList = ordermMapper.selectByCompany(order);
		model.addAttribute("allOrder", ordermList);
		model.addAttribute("order", order);
		return "forward:/companySys/orderMana.jsp";
	}
	//订单详情
	@RequestMapping("/companySys/orderDetail")
	public String orderDetail(Model model,Orderm order){
		Orderm orderm = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		model.addAttribute("orderm", orderm);
		return "forward:/companySys/orderDetail.jsp";
	}
}
