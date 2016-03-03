package com.server.action.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.Customer;
/**
 * 收藏
 * @author taolichao
 *
 */
@Controller
public class CollectController {
	@Autowired
	private CustomerMapper customerMapper;
	//到收藏页面
	@RequestMapping("/guliwang/doCollect")
	public String doCollect(Model model,String comid){
		Customer customer = customerMapper.selectCollectGoodsById(comid);	//根据客户id查询收藏商品
		model.addAttribute("customerCollect", customer);
		return "forward:/guliwang/collect.jsp";
	}
	//删除收藏品
	@RequestMapping("/guliwang/delCollect")
	public String delCollect(String[] collectids){
		for (String str : collectids) {
			System.out.println(str);
		}
		return "doCollect.action";
	}
}
