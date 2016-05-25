package com.server.action.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CollectMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.pojo.entity.Customer;
/**
 * 收藏
 * @author taolichao
 *
 */
@Controller
public class CollectController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CollectMapper collectMapper;
	//收藏页
	@RequestMapping("/guliwang/cusCollectInfo")
	@ResponseBody
	public Customer cusCollectInfo(Model model,String comid,String pricesclass){
		Customer customer = customerMapper.selectCollectGoodsById(comid,pricesclass);	//根据客户id查询收藏商品
		return customer;
	}
	//删除收藏品
	@RequestMapping("/guliwang/delCollect")
	public String delCollect(String[] collectids,String comid){
		if(collectids != null && collectids.length != 0){
			for (String str : collectids) {
				collectMapper.deleteByPrimaryKey(str);
			}
		}
		return "forward:collect.jsp";
	}
}
