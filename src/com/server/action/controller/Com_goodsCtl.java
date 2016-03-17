package com.server.action.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.PricesMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Prices;
import com.server.pojo.entity.Timegoods;
import com.system.tools.util.CommonUtil;

/**
 * 供应商后台管理系统-商品管理
 * @author tao
 *
 */
@Controller
public class Com_goodsCtl {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private TimegoodsMapper timegoodsMapper;
	@Autowired
	private PricesMapper pricesMapper;
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
	//到商品价格页面
	@RequestMapping("/companySys/doGoodsPrices")
	public String doGoodsPrices(Model model,Goods goodsCon){
		goodsCon = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
		model.addAttribute("goodsCon", goodsCon);
		return "forward:/companySys/goodsPrices.jsp";
	}
	//设置商品价格
		@RequestMapping("/companySys/addGoodsPrices")
		public String addGoodsPrices(Model model,Goods goodsCon,Prices prices){
			goodsCon = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
			String[] priceStrs = prices.getPricesprice().split(",");
			String[] price2Strs = prices.getPricesprice2().split(",");
			
			if(goodsCon.getPricesList() == null || goodsCon.getPricesList().size() < 9){
				//如果没有价格
				for (int i = 0; i < priceStrs.length; i++) {
					for (int j = 0; j < price2Strs.length; j++) {
						if(i == j){
							prices.setPricesprice(priceStrs[i]);				//单价
							prices.setPricesprice2(price2Strs[j]);				//套装价
							if(i < 3){
								prices.setPricesclass("餐饮客户");				//分类
							} else if(i>=3 && i<6){
								prices.setPricesclass("高级客户");
							} else if(i>=6 && i<9){
								prices.setPricesclass("组织单位客户");
							}
							prices.setPriceslevel(3-(i%3));						//等级
							java.text.DateFormat yyyy = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							prices.setCreatetime(yyyy.format(new Date()).toString());		//创建时间
							prices.setPricesid(CommonUtil.getNewId());			//价格id
							pricesMapper.insertSelective(prices);				//添加到数据库
						}
					} 
				}
			} else {
				//如果有价格
				String[] pricesIds = prices.getPricesid().split(",");
				for (int i = 0; i < priceStrs.length; i++) {
					for (int j = 0; j < price2Strs.length; j++) {
						for (int k = 0; k < pricesIds.length; k++) {
							if(i == j && i==k){
								prices.setPricesprice(priceStrs[i]);				//单价
								prices.setPricesprice2(price2Strs[j]);				//套装价
								if(i < 3){
									prices.setPricesclass("餐饮客户");				//分类
								} else if(i>=3 && i<6){
									prices.setPricesclass("高级客户");
								} else if(i>=6 && i<9){
									prices.setPricesclass("组织单位客户");
								}
								prices.setPriceslevel(3-(i%3));						//等级
								java.text.DateFormat yyyy = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								prices.setCreatetime(yyyy.format(new Date()).toString());		//创建时间
								prices.setPricesid(pricesIds[k]);					//价格id
								pricesMapper.updateByPrimaryKeySelective(prices);	//修改
							}
						}
					} 
				}
			}
			model.addAttribute("goodsCon", goodsCon);
			return "forward:allGoods.action";
		}
		//修改商品状态
		@RequestMapping("/companySys/putaway")
		public String putaway(Model model,Goods goodsCon){
			goodsMapper.updateByPrimaryKeySelective(goodsCon);
			model.addAttribute("goodsCon", goodsCon);
			return "forward:allGoods.action";
		}
	
}









