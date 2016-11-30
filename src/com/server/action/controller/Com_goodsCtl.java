package com.server.action.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.GivegoodsMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.GoodsclassMapper;
import com.server.dao.mapper.LargecuspriceMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.PricesMapper;
import com.server.dao.mapper.ScantMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Givegoods;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Goodsclass;
import com.server.pojo.entity.Largecusprice;
import com.server.pojo.entity.Orderd;
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
	@Autowired
	private LargecuspriceMapper largecuspriceMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CcustomerMapper ccustomreMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private OrderdMapper orderdMapper;
	
	//得到一段时间内订单的品牌
	@RequestMapping(value="/companySys/timeOrderdGoodsBrand")
	@ResponseBody
	public Map<String,Object> timeOrderdGoodsBrand(String staTime,String endTime,String companyid){
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> names = orderdMapper.selectTimeOrderdGoodsBrand(staTime, endTime, companyid);
		if(names.size()>0){
			map.put("brand", names);
			map.put("msg", "success");
		} else {
			map.put("msg", "error");
		}
		return map;
	}
	//设置订单商品的orderdbrand(商品品牌)
	@RequestMapping("/companySys/setOrderdBrand")
	@ResponseBody
	public Map<String, Object> setOrderdBrand(String timegoodsid){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		List<Orderd> msodLi = orderdMapper.selectAllOrderdBrandIsNull("秒杀");
		if(null != msodLi && msodLi.size()>0){
			for (int i = 0; i < msodLi.size(); i++) {
				Orderd od = msodLi.get(i);
				if(null!=od && null != od.getOrderm()){
					Timegoods tg = timegoodsMapper.selectByCodeUnitsCom(
							od.getOrderm().getOrdermcompany(), 
							od.getOrderdcode(), 
							od.getOrderdunits());
					if(null != tg){
						od.setOrderdbrand(tg.getTimegoodsbrand());
						count += orderdMapper.updateByPrimaryKeySelective(od);
					}/* else {
						System.out.println(od.getOrderdid());
					}*/
				}
			}
		}
		List<Orderd> mzodLi = orderdMapper.selectAllOrderdBrandIsNull("买赠");
		if(null != mzodLi && mzodLi.size()>0){
			for (int i = 0; i < mzodLi.size(); i++) {
				Orderd od = mzodLi.get(i);
				if(null!=od && null != od.getOrderm()){
					Givegoods gg = givegoodsMapper.selectByCodeUnitsCom(
							od.getOrderm().getOrdermcompany(), 
							od.getOrderdcode(), 
							od.getOrderdunits());
					if(null != gg){
						od.setOrderdbrand(gg.getGivegoodsbrand());
						count += orderdMapper.updateByPrimaryKeySelective(od);
					}/* else {
						System.out.println(od.getOrderdid());
					}*/
				}
			}
		}
		List<Orderd> godLi = orderdMapper.selectAllOrderdBrandIsNull("商品");
		if(null != godLi && godLi.size()>0){
			for (int i = 0; i < godLi.size(); i++) {
				Orderd od = godLi.get(i);
				if(null!=od && null != od.getOrderm()){
					Goods g = goodsMapper.selectByGoodsCode(
							od.getOrderdcode(), 
							od.getOrderm().getOrdermcompany(), 
							od.getOrderdunits());
					if(null != g){
						od.setOrderdbrand(g.getGoodsbrand());
						count += orderdMapper.updateByPrimaryKeySelective(od);
					}/* else {
						System.out.println(od.getOrderdid());
					}*/
				}
			}
		}
		map.put("msg", count);
		return map;
	}
	//设置订单商品的orderdgoods(商品id)
	@RequestMapping("/companySys/setOrderdgoodsid")
	@ResponseBody
	public Map<String, Object> setOrderdgoodsid(String timegoodsid){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		List<Orderd> odLi = orderdMapper.selectAllOrderdGoodsIsNull();		//商品id为空的订单
		for (int i = 0; i < odLi.size(); i++) {
			Orderd od = odLi.get(i);
			if(null!=od && null != od.getOrderm()){
				Goods gd = goodsMapper.selectByGoodsCode(
						od.getOrderdcode(), 
						od.getOrderm().getOrdermcompany(), 
						od.getOrderdunits());
				if(null != gd){
					od.setOrderdgoods(gd.getGoodsid());
					count += orderdMapper.updateByPrimaryKeySelective(od);
				}
			}
		}
		map.put("msg", count);
		return map;
	}
	//全部商品
	@RequestMapping("/companySys/allGoods")
	public String allGoods(Model model,Goods goodsCon,Integer pagenow){
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
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Goods> goodsList = goodsMapper.selectAllCanyinGoods(goodsCon,pagenow,10);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/goodsMana.jsp";
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
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Goods> goodsList = goodsMapper.selectAllCanyinGoods(goodsCon,pagenow,10);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCon", goodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/canyinGoodsMana.jsp";
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
		if(pagenow > pageCount){
			pagenow = pageCount;
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
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Givegoods> givegoodsList = givegoodsMapper.selectByCondition(givegoodsCon,pagenow,10);
		model.addAttribute("givegoodsList", givegoodsList);
		model.addAttribute("givegoodsCon", givegoodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/GivegoodsMana.jsp";
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
	//修改秒杀商品页面
	@RequestMapping("/companySys/doEditTimeGoods")
	public String doEditTimeGoods(Model model,Timegoods timegoodsCon,Integer pagenow){
		Timegoods editTimeGoods = timegoodsMapper.selectByPrimaryKey(timegoodsCon.getTimegoodsid());
		model.addAttribute("timegoodsCon", timegoodsCon);
		model.addAttribute("editTimeGoods", editTimeGoods);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/editTimeGoods.jsp";
	}
	//修改秒杀商品
	@RequestMapping("/companySys/editTimeGoods")
	@ResponseBody
	public String editTimeGoods(Timegoods editTimeGoods){
		if(editTimeGoods.getTimegoodsseq() == null){
			editTimeGoods.setTimegoodsseq(0);
		}
		if(editTimeGoods.getTimegoodsweight()==null || editTimeGoods.getTimegoodsweight().equals("")){
			editTimeGoods.setTimegoodsweight("0");
		}
		int upd = timegoodsMapper.updateByPrimaryKeySelective(editTimeGoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//删除秒杀商品
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
		model.addAttribute("givegoodsCon", givegoodsCon);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/editGiveGoods.jsp";
	}
	//修改买赠商品
	@RequestMapping("/companySys/editGiveGoods")
	@ResponseBody
	public String editGiveGoods(Givegoods editGiveGoods){
		if(editGiveGoods.getGivegoodsseq() == null){
			editGiveGoods.setGivegoodsseq(0);
		}
		if(editGiveGoods.getGivegoodsweight()==null || editGiveGoods.getGivegoodsweight().equals("")){
			editGiveGoods.setGivegoodsweight("0");
		}
		int upd = givegoodsMapper.updateByPrimaryKeySelective(editGiveGoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//删除买赠商品
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
	public Map<String, Object> addGoodsPrices(Goods editPriGoods,Prices prices,String strpricesprice,String strpricesprice2){
		Map<String, Object> map = new HashMap<String, Object>();
		Goods updateGoods = goodsMapper.selectByPrimaryKey(editPriGoods.getGoodsid());
		String[] priceStrs = strpricesprice.split(",");
		String[] price2Strs = strpricesprice2.split(",");
		String[] creator = prices.getCreator().split(",");
		prices.setPricesprice2(null);
		if(updateGoods.getPricesList() == null || updateGoods.getPricesList().size() < 9){
			//如果没有价格
			for (int i = 0; i < priceStrs.length; i++) {
				for (int j = 0; j < price2Strs.length; j++) {
					if(i == j && !price2Strs[j].equals("")){
						prices.setPricesprice2(Float.valueOf(price2Strs[j]) );				//套装价
					}
				} 
				if(creator[i/3].equals("1")){
					prices.setCreator("启用");					//价格状态设置为启用
				} else if (creator[i/3].equals("0")){
					prices.setCreator("禁用");					//价格状态设置为禁用
				}
				prices.setPricesprice(Float.valueOf(priceStrs[i]));				//单价
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
								prices.setPricesprice(Float.valueOf(priceStrs[i]));				//单价
								prices.setPricesprice2(Float.valueOf(price2Strs[j]));				//套装价
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
							prices.setPricesprice(Float.valueOf(priceStrs[i]));				//单价
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
		if(pagenowGoods > pageCountGoods){
			pagenowGoods = pageCountGoods;
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
	@ResponseBody
	public Map<String, Object> addGoods(Goods goodsCon){
		Map<String, Object> map = new HashMap<String, Object>();
		Goods reGoods = goodsMapper.selectByGoodsCode(goodsCon.getGoodscode(), goodsCon.getGoodscompany(),goodsCon.getGoodsunits());
		if(null==reGoods || reGoods.getGoodsid().equals(goodsCon.getGoodsid())){
			if(goodsCon.getGoodsorder() == null){
				goodsCon.setGoodsorder(0);
			}
			if(goodsCon.getGoodsweight() == null || goodsCon.getGoodsweight().equals("")){
				goodsCon.setGoodsweight("0");
			}
			goodsCon.setGoodsstatue("下架");
			goodsCon.setCreatetime(DateUtils.getDateTime());
			goodsCon.setGoodsid(CommonUtil.getNewId());
			int insNum = goodsMapper.insertSelective(goodsCon);
			if(insNum ==1){
				map.put("goods", goodsCon);
				map.put("message", "success");
			} else {
				map.put("message", "fail1");		//操作失败,没有添加商品。
			}
		} else {
			map.put("message", "fail2");			//商品编号和规格重复，添加失败。
		}
		return map;
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
		Goods reGoods = goodsMapper.selectByGoodsCode(editGoods.getGoodscode(), editGoods.getGoodscompany(),editGoods.getGoodsunits());
		if(null==reGoods || reGoods.getGoodsid().equals(editGoods.getGoodsid())){
			if(editGoods.getGoodsorder() == null){
				editGoods.setGoodsorder(0);
			}
			if(editGoods.getGoodsweight() == null || editGoods.getGoodsweight().equals("")){
				editGoods.setGoodsweight("0");
			}
			int upd = goodsMapper.updateByPrimaryKeySelective(editGoods);
			if(upd > 0){
				return "ok";
			} else {
				return "error1";
			}
		} else {
			return "no";					//编号重复,请重新输入.
		}
	}
	//查询标品
	@RequestMapping(value="/companySys/getallScant",produces = "application/json")
	@ResponseBody
	public Map<String,Object> getallScant(Integer nowpageScant,String scantcondition){
		Map<String,Object> map = new HashMap<String, Object>();
		if(nowpageScant == null){
			nowpageScant = 1;
		}
		Integer countScant = scantMapper.selectAllScantNum(scantcondition);
		Integer pageCountScant;		//总页数
		if(countScant % 10 ==0){
			pageCountScant = countScant / 10;
		} else {
			pageCountScant = (countScant / 10) +1;
		}
		if(nowpageScant > pageCountScant){
			nowpageScant = pageCountScant;
		}
		List<Scant> Scantlist = scantMapper.selectAllScant(nowpageScant,10,scantcondition);
		map.put("pageCountScant", pageCountScant);
		map.put("countScant", countScant);
		map.put("nowpageScant", nowpageScant);
		map.put("Scantlist", Scantlist);
		return map;
	}
	//得到全部小类
	@RequestMapping(value="/companySys/getallGoodclass",produces = "application/json")
	@ResponseBody
	public List<Goodsclass> getallGoodclass(String companyid){
		List<Goodsclass> list = goodsclassMapper.selectAllGoodsclass(companyid);
		return list;
	}
	//添加秒杀商品
	@RequestMapping(value="/companySys/addTimeGoods",produces = "application/json")
	@ResponseBody
	public Map<String, Object> addTimeGoods(Timegoods timegoods){
		Map<String, Object> map = new HashMap<String, Object>();
		if(timegoodsMapper.selectComRepeatGoods(timegoods.getTimegoodscompany(), timegoods.getTimegoodscode(), timegoods.getTimegoodsunits())==0){
			if(timegoods.getTimegoodsseq() == null){
				timegoods.setTimegoodsseq(0);
			}
			if(timegoods.getTimegoodsweight()==null || timegoods.getTimegoodsweight().equals("")){
				timegoods.setTimegoodsweight("0");
			}
			timegoods.setTimegoodsid(CommonUtil.getNewId());
			timegoods.setCreatetime(DateUtils.getDateTime());
			timegoods.setSurplusnum(timegoods.getAllnum());
			timegoods.setTimegoodsstatue("启用");
			timegoodsMapper.insertSelective(timegoods);
			map.put("message", "success");
		} else {
			map.put("message", "fail2");		//商品编号和规格重复，添加失败。
		}
		return map;
	}
	//添加买赠商品
	@RequestMapping(value="/companySys/addGiveGoods",produces = "application/json")
	@ResponseBody
	public Map<String, Object> addGiveGoods(Givegoods givegoods){
		Map<String, Object> map = new HashMap<String, Object>();
		if(givegoodsMapper.selectComRepeatGoods(givegoods.getGivegoodscompany(), givegoods.getGivegoodscode(), givegoods.getGivegoodsunits())==0){
			if(givegoods.getGivegoodsseq() == null){
				givegoods.setGivegoodsseq(0);
			}
			if(givegoods.getGivegoodsweight()==null || givegoods.getGivegoodsweight().equals("")){
				givegoods.setGivegoodsweight("0");
			}
			givegoods.setGivegoodsid(CommonUtil.getNewId());
			givegoods.setCreatetime(DateUtils.getDateTime());
			givegoods.setGivegoodsstatue("启用");
			givegoodsMapper.insertSelective(givegoods);
			map.put("message", "success");
		} else {
			map.put("message", "fail2");		//商品编号和规格重复，添加失败。
		}
		return map;
	}
	//添加录单客户订单时所需商品
	@RequestMapping(value="/companySys/getlargeCusGoods",produces = "application/json")
	@ResponseBody
	public Map<String,Object> getlargeCusGoods(Goods goodsCon,Integer pagenowGoods,String customerid){
		Map<String,Object> map = new HashMap<String, Object>();
		if(pagenowGoods == null){
			pagenowGoods = 1;
		}
		Integer countGoods = goodsMapper.selectlagerCusGoodsCount(goodsCon,customerid);	//总信息条数
		Integer pageCountGoods;		//总页数
		if(countGoods % 10 ==0){
			pageCountGoods = countGoods / 10;
		} else {
			pageCountGoods = (countGoods / 10) +1;
		}
		List<Goods> goodsList = goodsMapper.selectlagerCusGoods(goodsCon, pagenowGoods, 10,customerid);
		map.put("pageCountGoods", pageCountGoods);
		map.put("countGoods", countGoods);
		map.put("pagenowGoods", pagenowGoods);
		map.put("goodsList", goodsList);
		return map;
	}
	//查询录单客户的特殊价格商品
	@RequestMapping(value="/companySys/querylargeCusPriceGoods")
	@ResponseBody
	public Map<String,Object> querylargeCusPriceGoods(Largecusprice largecusprice,Integer pagenow){
		Map<String,Object> map = new HashMap<String, Object>();
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = largecuspriceMapper.selectLargeCusPriceGoodsCount(largecusprice);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Largecusprice> largeCPList = largecuspriceMapper.selectLargeCusPriceGoods(largecusprice, pagenow, 10);
		map.put("pageCount", pageCount);
		map.put("count", count);
		map.put("pagenow", pagenow);
		map.put("largeCusPrice", largeCPList);
		return map;
	}
	//添加录单客户商品价格
	@RequestMapping(value="/companySys/saveNewLargeCusGP")
	@ResponseBody
	public Integer saveNewLargeCusGP(Largecusprice newLargecusprice){
		Integer num = largecuspriceMapper.selectCusBargainByGoodsId(newLargecusprice);
		if(num>0){
			return 0;
		}
		newLargecusprice.setLargecuspriceid(CommonUtil.getNewId());
		newLargecusprice.setLargecuspricecreatetime(DateUtils.getDateTime());
		return largecuspriceMapper.insertSelective(newLargecusprice);
	}
	//修改录单客户商品价格
	@RequestMapping(value="/companySys/editLargeGoodsPrice")
	@ResponseBody
	public Integer editLargeGoodsPrice(Largecusprice largecusprice){
		largecusprice.setLargecuspricecreator(DateUtils.getDateTime());
		return largecuspriceMapper.updateByPrimaryKeySelective(largecusprice);
	}
	//删除录单客户商品
	@RequestMapping(value="/companySys/deleteLGPByIDs")
	@ResponseBody
	public String deleteLGPByIDs(@RequestParam("lcpIDs[]") String[] lcpIDs){
		for (String lcpID : lcpIDs) {
			largecuspriceMapper.deleteByPrimaryKey(lcpID);
		}
		return "";
	}
	//添加录单客户
	@RequestMapping(value="/companySys/saveLargeCus")
	@ResponseBody
	public String saveLargeCus(Customer cus,String comid){
		String newId = CommonUtil.getNewId();
		String datatime = DateUtils.getDateTime();
		cus.setCustomerid(newId);
		cus.setOpenid(newId);
		cus.setCreatetime(datatime);
		cus.setCustomerstatue("启用");
		/*if(cus.getCustomercity() == null || cus.getCustomercity().equals("")){		//如果是
			cus.setCustomercity("嘉兴市");
			cus.setCustomerxian("海盐县");
			cus.setCustomertype("3");
		}*/
		if(cus.getCustomerlevel()>3|| cus.getCustomerlevel()<1){
			cus.setCustomerlevel(3);
		}
		customerMapper.insertSelective(cus);
		//添加新地址
		Address address = new Address();
		address.setAddressture(1);							//自动设为默认地址
		address.setAddressid(newId);		//设置新id
		address.setAddressaddress(cus.getCustomercity()+cus.getCustomerxian()+cus.getCustomeraddress());
		address.setAddresscustomer(newId);				//客户id
		address.setAddressphone(cus.getCustomerphone());
		address.setAddressconnect(cus.getCustomername());
		addressMapper.insertSelective(address);				//添加默认地址
		//添加与唯一客户的关系
		Ccustomer newccustomer = new Ccustomer();
		newccustomer.setCcustomerid(newId);
		newccustomer.setCcustomercompany(comid);
		newccustomer.setCcustomercustomer(newId);
		newccustomer.setCcustomerdetail(cus.getCustomerlevel().toString());
		newccustomer.setCreator("1");
		ccustomreMapper.insertSelective(newccustomer);
		return "";
	}
	//查询地区
	@RequestMapping(value="/companySys/lcpQueryCity", produces="application/json")
	@ResponseBody 
	public List<City> lcpQueryCity(City cityNameOrKey){
		List<City> cityList = null;
		if(cityNameOrKey.getCityid() != null || cityNameOrKey.getCityname() != null ){
			City parentCity = cityMapper.selectByCitynameOrKey(cityNameOrKey).get(0);
			cityList = cityMapper.selectByCityparent(parentCity.getCityid());
		}
		return cityList;
	}
}

















