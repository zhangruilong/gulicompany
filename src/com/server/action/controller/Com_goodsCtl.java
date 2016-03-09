package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.GoodsMapper;
import com.server.pojo.entity.Goods;

/**
 * 供应商后台管理系统-订单管理
 * @author tao
 *
 */
@Controller
public class Com_goodsCtl {
	@Autowired
	private GoodsMapper goodsMapper;
	//全部商品
	@RequestMapping("/companySys/allGoods")
	public String allGoods(Model model,Goods goods){
		List<Goods> goodsList = goodsMapper.selectByCondition(goods);
		model.addAttribute("goodsList", goodsList);
		return "forward:/companySys/goodsMana.jsp";
	}
}









