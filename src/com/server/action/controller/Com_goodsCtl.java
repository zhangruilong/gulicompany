package com.server.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Timegoods;

/**
 * 供应商后台管理系统-订单管理
 * @author tao
 *
 */
@Controller
public class Com_goodsCtl {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private TimegoodsMapper timegoodsMapper;
	//全部商品
	@RequestMapping("/companySys/allGoods")
	public String allGoods(Model model,Goods goodsCon){
		List<Goods> goodsList = goodsMapper.selectByCondition(goodsCon);
		model.addAttribute("goodsList", goodsList);
		
		model.addAttribute("goodsCon", goodsCon);
		return "forward:/companySys/goodsMana.jsp";
	}
	//全部促销品
		@RequestMapping("/companySys/allTimeGoods")
		public String allTimeGoods(Model model,Timegoods timegoodsCon){
			List<Timegoods> timegoodsList = timegoodsMapper.selectByCondition(timegoodsCon);
			model.addAttribute("timegoodsList", timegoodsList);
			return "forward:/companySys/TimegoodsMana.jsp";
		}
}









