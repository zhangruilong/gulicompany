package com.server.action.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.GivegoodsMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.GoodsclassMapper;
import com.server.dao.mapper.PricesMapper;
import com.server.dao.mapper.ScantMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Givegoods;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Goodsclass;
import com.server.pojo.entity.Prices;
import com.server.pojo.entity.Scant;
import com.server.pojo.entity.Timegoods;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

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
	private GoodsclassMapper goodsclassMapper;
	@Autowired
	private TimegoodsMapper timegoodsMapper;
	@Autowired
	private PricesMapper pricesMapper;
	@Autowired
	private ScantMapper scantMapper;
	@Autowired
	private GivegoodsMapper givegoodsMapper;
	//全部商品
	@RequestMapping("/companySys/allGoods")
	public String allGoods(Model model,Goods goodsCon,Integer pagenow){
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = goodsMapper.selectByConditionCount(goodsCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Goods> goodsList = goodsMapper.selectByCondition(goodsCon,pagenow,10);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/goodsMana.jsp";
	}
	//全部促销品
	@RequestMapping("/companySys/allTimeGoods")
	public String allTimeGoods(Model model,Timegoods timegoodsCon,Integer pagenow){
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = timegoodsMapper.selectByConditionCount(timegoodsCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Timegoods> timegoodsList = timegoodsMapper.selectByCondition(timegoodsCon,pagenow,10);
		model.addAttribute("timegoodsList", timegoodsList);
		model.addAttribute("timegoodsCon", timegoodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/TimegoodsMana.jsp";
	}
	//全部买赠商品
	@RequestMapping("/companySys/allGiveGoods")
	public String allGiveGoods(Model model,Givegoods givegoodsCon,Integer pagenow){
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = givegoodsMapper.selectByConditionCount(givegoodsCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Givegoods> givegoodsList = givegoodsMapper.selectByCondition(givegoodsCon,pagenow,10);
		model.addAttribute("givegoodsList", givegoodsList);
		model.addAttribute("givegoodsCon", givegoodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/GivegoodsMana.jsp";
	}
	//全部餐饮商品
	@RequestMapping("/companySys/allCanyinGoods")
	public String allCanyinGoods(Model model,Goods goodsCon,Integer pagenow){
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = goodsMapper.selectAllCanyinGoodsCount(goodsCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Goods> goodsList = goodsMapper.selectAllCanyinGoods(goodsCon,pagenow,10);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/canyinGoodsMana.jsp";
	}
	//到商品价格页面
	@RequestMapping("/companySys/doGoodsPrices")
	public String doGoodsPrices(Model model,Goods goodsCon,Integer pagenow){
		Goods editPriGoods = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
		model.addAttribute("editPriGoods", editPriGoods);
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/goodsPrices.jsp";
	}
	//修改 秒杀 商品页面
	@RequestMapping("/companySys/doEditTimeGoods")
	public String doEditTimeGoods(Model model,Timegoods timegoodsCon,Integer pagenow){
		Timegoods editTimeGoods = timegoodsMapper.selectByPrimaryKey(timegoodsCon.getTimegoodsid());
		model.addAttribute("editTimeGoods", editTimeGoods);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/editTimeGoods.jsp";
	}
	//修改 秒杀 商品
	@RequestMapping("/companySys/editTimeGoods")
	@ResponseBody
	public String editTimeGoods(Timegoods editTimeGoods){
		int upd = timegoodsMapper.updateByPrimaryKeySelective(editTimeGoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//删除 秒杀 商品
	@RequestMapping("/companySys/removeTimeGoods")
	@ResponseBody
	public String removeTimeGoods(String timegoodsid){
		int del = timegoodsMapper.deleteByPrimaryKey(timegoodsid);
		if(del > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//修改 买赠 商品页面
	@RequestMapping("/companySys/doEditGiveGoods")
	public String doEditGiveGoods(Model model,Givegoods givegoodsCon,Integer pagenow){
		Givegoods editGiveGoods = givegoodsMapper.selectByPrimaryKey(givegoodsCon.getGivegoodsid());
		model.addAttribute("editGiveGoods", editGiveGoods);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/editGiveGoods.jsp";
	}
	//修改 买赠 商品
	@RequestMapping("/companySys/editGiveGoods")
	@ResponseBody
	public String editGiveGoods(Givegoods editGiveGoods){
		int upd = givegoodsMapper.updateByPrimaryKeySelective(editGiveGoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//删除 买赠 商品
	@RequestMapping("/companySys/removeGiveGoods")
	@ResponseBody
	public String removeGiveGoods(String givegoodsid){
		int del = givegoodsMapper.deleteByPrimaryKey(givegoodsid);
		if(del > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//设置商品价格
	@RequestMapping("/companySys/addGoodsPrices")
	@ResponseBody
	public Map<String, Object> addGoodsPrices(Goods editPriGoods,Prices prices){
		Map<String, Object> map = new HashMap<String, Object>();
		Goods updateGoods = goodsMapper.selectByPrimaryKey(editPriGoods.getGoodsid());
		String[] priceStrs = prices.getPricesprice().split(",");
		String[] price2Strs = prices.getPricesprice2().split(",");
		String[] creator = prices.getCreator().split(",");
		prices.setPricesprice2(null);
		if(updateGoods.getPricesList() == null || updateGoods.getPricesList().size() < 9){
			//如果没有价格
			for (int i = 0; i < priceStrs.length; i++) {
				for (int j = 0; j < price2Strs.length; j++) {
					if(i == j){
						prices.setPricesprice2(price2Strs[j]);				//套装价
					}
				} 
				if(creator[i/3].equals("1")){
					prices.setCreator("启用");					//价格状态设置为启用
				} else if (creator[i/3].equals("0")){
					prices.setCreator("禁用");					//价格状态设置为禁用
				}
				prices.setPricesprice(priceStrs[i]);				//单价
				if(i < 3){
					prices.setPricesclass("3");				//分类
				} else if(i>=3 && i<6){
					prices.setPricesclass("2");
				} else if(i>=6 && i<9){
					prices.setPricesclass("1");
				}
				prices.setPriceslevel(3-(i%3));						//等级
				prices.setCreatetime(DateUtils.getDateTime());		//创建时间
				prices.setPricesid(CommonUtil.getNewId());			//价格id
				pricesMapper.insertSelective(prices);				//添加到数据库
		}
		} else {
			//如果有价格
			String[] pricesIds = prices.getPricesid().split(",");
			if(price2Strs.length == 9){										//有套装价的情况
				for (int i = 0; i < priceStrs.length; i++) {
					for (int j = 0; j < price2Strs.length; j++) {
						for (int k = 0; k < pricesIds.length; k++) {
							if(i == j && i==k){
								prices.setPricesprice(priceStrs[i]);				//单价
								prices.setPricesprice2(price2Strs[j]);				//套装价
								if(i < 3){
									prices.setPricesclass("3");				//分类
								} else if(i>=3 && i<6){
									prices.setPricesclass("2");
								} else if(i>=6 && i<9){
									prices.setPricesclass("1");
								}
								if(creator[i/3].equals("1")){
									prices.setCreator("启用");					//价格状态设置为启用
								} else if (creator[i/3].equals("0")){
									prices.setCreator("禁用");					//价格状态设置为禁用
								}
								prices.setPriceslevel(3-(i%3));						//等级
								prices.setUpdtime(DateUtils.getDateTime());		//修改时间
								prices.setPricesid(pricesIds[k]);					//价格id
								pricesMapper.updateByPrimaryKeySelective(prices);	//修改
							}
						}
					} 
				}
			} else {				//没有套装价的情况
				for (int i = 0; i < priceStrs.length; i++) {
					for (int k = 0; k < pricesIds.length; k++) {
						if(i==k){
							prices.setPricesprice(priceStrs[i]);				//单价
							if(i < 3){
								prices.setPricesclass("3");				//分类
							} else if(i>=3 && i<6){
								prices.setPricesclass("2");
							} else if(i>=6 && i<9){
								prices.setPricesclass("1");
							}
							if(creator[i/3].equals("1")){
								prices.setCreator("启用");					//价格状态设置为启用
							} else if (creator[i/3].equals("0")){
								prices.setCreator("禁用");					//价格状态设置为禁用
							}
							prices.setPriceslevel(3-(i%3));						//等级
							prices.setUpdtime(DateUtils.getDateTime());		//修改时间
							prices.setPricesid(pricesIds[k]);					//价格id
							pricesMapper.updateByPrimaryKeySelective(prices);	//修改
						}
					}
				}
			}
		}
		updateGoods.setUpdtime(DateUtils.getDateTime());
		goodsMapper.updateByPrimaryKeySelective(updateGoods);
		return map;
	}
	//修改商品状态
	@RequestMapping("/companySys/putaway")
	@ResponseBody
	public Map<String, Object> putaway(Goods goodsCon){
		Map<String, Object> map = new HashMap<String, Object>();
		String editResult;										//要返回的提示信息
		Goods putawayGoods = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
		List<Prices> pricesList = putawayGoods.getPricesList();
		if(pricesList.size() == 9){								//判断是否设置了9个价格
			goodsCon.setUpdtime(DateUtils.getDateTime());		//设置修改时间
			goodsMapper.updateByPrimaryKeySelective(goodsCon);
			editResult = "修改成功！";
		} else {
			editResult = "修改失败，请先设置价格。";
		}
		map.put("editResult", editResult);
		return map;
	}
	//全部商品
	@RequestMapping(value="/companySys/getallGoods",produces = "application/json")
	@ResponseBody
	public Map<String,Object> getallGoods(Goods goodsCon,Integer pagenowGoods){
		Map<String,Object> map = new HashMap<String, Object>();
		if(pagenowGoods == null){
			pagenowGoods = 1;
		}
		Integer countGoods = goodsMapper.selectByConditionCount(goodsCon);	//总信息条数
		Integer pageCountGoods;		//总页数
		if(countGoods % 10 ==0){
			pageCountGoods = countGoods / 10;
		} else {
			pageCountGoods = (countGoods / 10) +1;
		}
		List<Goods> goodsList = goodsMapper.selectByCondition(goodsCon,pagenowGoods,10);
		map.put("pageCountGoods", pageCountGoods);
		map.put("countGoods", countGoods);
		map.put("pagenowGoods", pagenowGoods);
		map.put("goodsList", goodsList);
		return map;
	}
	//添加商品
	@RequestMapping("/companySys/addGoods")
	public String addGoods(Model model,Goods goodsCon){
		if(goodsCon.getGoodsorder() == null){
			goodsCon.setGoodsorder(0);
		}
		goodsCon.setGoodsstatue("下架");
		goodsCon.setCreatetime(DateUtils.getDateTime());
		goodsCon.setGoodsid(CommonUtil.getNewId());
		goodsMapper.insertSelective(goodsCon);
		//model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("editPriGoods", goodsCon);
		model.addAttribute("message", "添加商品");
		return "forward:/companySys/goodsPrices.jsp";
	}
	//修改商品页面
	@RequestMapping("/companySys/doEditGoods")
	public String doEditGoods(Model model,Goods goodsCon,Integer pagenow){
		Goods editGoods = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("editGoods", editGoods);
		return "forward:/companySys/editGoods.jsp";
	}
	//修改商品
	@RequestMapping("/companySys/editGoods")
	@ResponseBody
	public String editGoods(Goods editGoods){
		int upd = goodsMapper.updateByPrimaryKeySelective(editGoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//查询标品
	@RequestMapping(value="/companySys/getallScant",produces = "application/json")
	@ResponseBody
	public Map<String,Object> getallScant(Integer nowpageScant){
		Map<String,Object> map = new HashMap<String, Object>();
		if(nowpageScant == null){
			nowpageScant = 1;
		}
		Integer countScant = scantMapper.selectAllScantNum(nowpageScant, 10);
		Integer pageCountScant;		//总页数
		if(countScant % 10 ==0){
			pageCountScant = countScant / 10;
		} else {
			pageCountScant = (countScant / 10) +1;
		}
		List<Scant> Scantlist = scantMapper.selectAllScant(nowpageScant,10);
		map.put("pageCountScant", pageCountScant);
		map.put("countScant", countScant);
		map.put("nowpageScant", nowpageScant);
		map.put("Scantlist", Scantlist);
		return map;
	}
	//得到全部小类
	@RequestMapping(value="/companySys/getallGoodclass",produces = "application/json")
	@ResponseBody
	public List<Goodsclass> getallGoodclass(){
		List<Goodsclass> list = goodsclassMapper.selectAllGoodsclass();
		return list;
	}
	//添加秒杀商品
	@RequestMapping(value="/companySys/addTimeGoods",produces = "application/json")
	@ResponseBody
	public Map<String, Object> addTimeGoods(Timegoods timegoods){
		Map<String, Object> map = new HashMap<String, Object>();
		if(timegoods.getTimegoodsseq() == null){
			timegoods.setTimegoodsseq(0);
		}
		timegoods.setTimegoodsid(CommonUtil.getNewId());
		timegoods.setCreatetime(DateUtils.getDateTime());
		timegoods.setSurplusnum(timegoods.getAllnum());
		timegoods.setTimegoodsstatue("启用");
		timegoodsMapper.insertSelective(timegoods);
		return map;
	}
	//添加买赠商品
	@RequestMapping(value="/companySys/addGiveGoods",produces = "application/json")
	@ResponseBody
	public Map<String, Object> addGiveGoods(Givegoods givegoods){
		Map<String, Object> map = new HashMap<String, Object>();
		if(givegoods.getGivegoodsseq() == null){
			givegoods.setGivegoodsseq(0);
		}
		givegoods.setGivegoodsid(CommonUtil.getNewId());
		givegoods.setCreatetime(DateUtils.getDateTime());
		givegoods.setGivegoodsstatue("启用");
		givegoodsMapper.insertSelective(givegoods);
		return map;
	}
	//添加大客户订单时所需商品
	@RequestMapping(value="/companySys/getlargeCusGoods",produces = "application/json")
	@ResponseBody
	public Map<String,Object> getlargeCusGoods(Goods goodsCon,Integer pagenowGoods){
		Map<String,Object> map = new HashMap<String, Object>();
		if(pagenowGoods == null){
			pagenowGoods = 1;
		}
		Integer countGoods = goodsMapper.selectlagerCusGoodsCount(goodsCon);	//总信息条数
		Integer pageCountGoods;		//总页数
		if(countGoods % 10 ==0){
			pageCountGoods = countGoods / 10;
		} else {
			pageCountGoods = (countGoods / 10) +1;
		}
		List<Goods> goodsList = goodsMapper.selectlagerCusGoods(goodsCon, pagenowGoods, 10);
		map.put("pageCountGoods", pageCountGoods);
		map.put("countGoods", countGoods);
		map.put("pagenowGoods", pagenowGoods);
		map.put("goodsList", goodsList);
		return map;
	}
}









