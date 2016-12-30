package com.server.action.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.BkgoodsMapper;
import com.server.dao.mapper.CcustomerMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.GoodsclassMapper;
import com.server.dao.mapper.LargecuspriceMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.PricesMapper;
import com.server.dao.mapper.ScantMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.Bkgoods;
import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Goods;
import com.server.pojo.entity.Goodsclass;
import com.server.pojo.entity.Largecusprice;
import com.server.pojo.entity.LoginInfo;
import com.server.pojo.entity.Prices;
import com.server.pojo.entity.Scant;
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
	private PricesMapper pricesMapper;
	@Autowired
	private ScantMapper scantMapper;
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
	@Autowired
	private BkgoodsMapper bkgoodsMapper;
	
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
	//分页查询年货、组合商品、秒杀商品、买赠商品
	@RequestMapping("/companySys/allCarnivalGoods")
	public String allCarnivalGoods(Model model,Bkgoods bkgoodsCon,Integer pagenow){
		if(pagenow == null || pagenow == 0){
			pagenow = 1;
		}
		Integer count = bkgoodsMapper.queryBkgoodsCount(bkgoodsCon);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Bkgoods> bkgoodsList = bkgoodsMapper.queryBkgoods(bkgoodsCon,pagenow,10);
		model.addAttribute("bkgoodsList", bkgoodsList);
		model.addAttribute("bkgoodsCon", bkgoodsCon);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		if(bkgoodsCon.getBkgoodstype().equals("年货")){
			return "forward:/companySys/carnivalgoodsMana.jsp";
		} else if(bkgoodsCon.getBkgoodstype().equals("秒杀")){
			return "forward:/companySys/TimegoodsMana.jsp";
		} else if(bkgoodsCon.getBkgoodstype().equals("买赠")){
			return "forward:/companySys/GivegoodsMana.jsp";
		} else {		//组合商品
			return "forward:/companySys/modularGoodsMana.jsp";
		}
		
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
	//修改年货商品、组合商品、买赠商品、秒杀商品页面
	@RequestMapping("/companySys/doEditBkgoods")
	public String doEditBkgoods(Model model,Bkgoods bkgoodsCon,Integer pagenow){
		Bkgoods editBkgoods = bkgoodsMapper.selectByPrimaryKey(bkgoodsCon.getBkgoodsid());
		model.addAttribute("bkgoodsCon", bkgoodsCon);
		model.addAttribute("editBkgoods", editBkgoods);
		model.addAttribute("pagenow", pagenow);
		if(bkgoodsCon.getBkgoodstype().equals("年货")){
			return "forward:/companySys/editCarnivalGoods.jsp";
		} else if(bkgoodsCon.getBkgoodstype().equals("秒杀")){
			return "forward:/companySys/editTimeGoods.jsp";
		} else if(bkgoodsCon.getBkgoodstype().equals("买赠")){
			return "forward:/companySys/editGiveGoods.jsp";
		} else {		//组合商品
			return "forward:/companySys/editModularGoods.jsp";
		}
	}
	//修改年货商品、组合商品、买赠商品、秒杀商品
	@RequestMapping("/companySys/editBkgoods")
	@ResponseBody
	public String editBkgoods(HttpSession session,Bkgoods editBkgoods){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");	//登录信息
		if(null == editBkgoods.getBkgoodsseq()){
			editBkgoods.setBkgoodsseq(0);
		}
		if(null==editBkgoods.getBkgoodsweight() || editBkgoods.getBkgoodsweight().equals("")){
			editBkgoods.setBkgoodsweight("0");
		}
		if(null==editBkgoods.getBkgoodsnum() || editBkgoods.getBkgoodsnum().equals("")){
			editBkgoods.setBkgoodsnum(-1);
		}
		if(null==editBkgoods.getBkgoodsallnum() || editBkgoods.getBkgoodsallnum().equals("")){
			editBkgoods.setBkgoodsallnum(-1);
		}
		if(null==editBkgoods.getBkgoodsprice()){
			editBkgoods.setBkgoodsprice(new Float(0.0));
		}
		editBkgoods.setBkgoodsupdtime(DateUtils.getDateTime());		//修改时间
		editBkgoods.setBkgoodsupdor(info.getUsername());			//修改人
		int upd = bkgoodsMapper.updateByPrimaryKeySelective(editBkgoods);
		if(upd > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	
	//删除年货商品、组合商品、买赠商品、秒杀商品
	@RequestMapping("/companySys/removeBkgoods")
	@ResponseBody
	public String removeBkgoods(String bkgoodsid){
		int del = bkgoodsMapper.deleteByPrimaryKey(bkgoodsid);
		if(del > 0){
			return "ok";
		} else {
			return "no";
		}
	}
	//设置商品价格
	@RequestMapping("/companySys/addGoodsPrices")
	@ResponseBody
	public Map<String, Object> addGoodsPrices(HttpSession session,Goods editPriGoods,Prices prices,String strpricesprice,String strpricesprice2){
		Map<String, Object> map = new HashMap<String, Object>();
		LoginInfo lInfo = (LoginInfo) session.getAttribute("loginInfo");		//登录信息
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
				prices.setCreator(lInfo.getUsername());						//创建人
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
								prices.setUpdor(lInfo.getUsername());						//修改人
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
							prices.setUpdor(lInfo.getUsername());						//修改人
							prices.setPricesid(pricesIds[k]);					//价格id
							pricesMapper.updateByPrimaryKeySelective(prices);	//修改
						}
					}
				}
			}
		}
		updateGoods.setUpdtime(DateUtils.getDateTime());	//修改时间
		updateGoods.setUpdor(lInfo.getUsername());		//修改人
		goodsMapper.updateByPrimaryKeySelective(updateGoods);
		return map;
	}
	//修改商品状态
	@RequestMapping("/companySys/putaway")
	@ResponseBody
	public Map<String, Object> putaway(HttpSession session,Goods goodsCon){
		Map<String, Object> map = new HashMap<String, Object>();
		LoginInfo lInfo = (LoginInfo) session.getAttribute("loginInfo");		//登录信息
		String editResult;										//要返回的提示信息
		Goods putawayGoods = goodsMapper.selectByPrimaryKey(goodsCon.getGoodsid());
		List<Prices> pricesList = putawayGoods.getPricesList();
		if(pricesList.size() == 9){								//判断是否设置了9个价格
			goodsCon.setUpdtime(DateUtils.getDateTime());		//修改时间
			goodsCon.setUpdor(lInfo.getUsername());			//修改人
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
	public Map<String, Object> addGoods(HttpSession session,Goods goodsCon){
		Map<String, Object> map = new HashMap<String, Object>();
		LoginInfo lInfo = (LoginInfo) session.getAttribute("loginInfo");
		Goods reGoods = goodsMapper.selectByGoodsCode(goodsCon.getGoodscode(), goodsCon.getGoodscompany(),goodsCon.getGoodsunits());
		if(null==reGoods || reGoods.getGoodsid().equals(goodsCon.getGoodsid())){
			if(goodsCon.getGoodsorder() == null){
				goodsCon.setGoodsorder(0);
			}
			if(goodsCon.getGoodsweight() == null || goodsCon.getGoodsweight().equals("")){
				goodsCon.setGoodsweight("0");
			}
			goodsCon.setGoodsstatue("下架");
			goodsCon.setCreatetime(DateUtils.getDateTime());		//创建时间
			goodsCon.setCreator(lInfo.getUsername());			//创建人
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
	public String editGoods(HttpSession session,Goods editGoods){
		LoginInfo lInfo = (LoginInfo) session.getAttribute("loginInfo");
		Goods reGoods = goodsMapper.selectByGoodsCode(editGoods.getGoodscode(), editGoods.getGoodscompany(),editGoods.getGoodsunits());
		if(null==reGoods || reGoods.getGoodsid().equals(editGoods.getGoodsid())){
			if(editGoods.getGoodsorder() == null){
				editGoods.setGoodsorder(0);
			}
			if(editGoods.getGoodsweight() == null || editGoods.getGoodsweight().equals("")){
				editGoods.setGoodsweight("0");
			}
			editGoods.setUpdtime(DateUtils.getDateTime());		//修改时间
			editGoods.setUpdor(lInfo.getUsername());			//修改人
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
	//添加年货商品、组合商品、买赠商品、秒杀商品
	@RequestMapping(value="/companySys/addBkgoods",produces = "application/json")
	@ResponseBody
	public Map<String, Object> addBkgoods(HttpServletRequest request,Bkgoods bkgoods){
		Map<String, Object> map = new HashMap<String, Object>();
		if(bkgoodsMapper.selectComRepeatGoods(bkgoods.getBkgoodscompany(), bkgoods.getBkgoodscode(), bkgoods.getBkgoodsunits(), bkgoods.getBkgoodstype())==0){
			if(null == bkgoods.getBkgoodsseq()){
				bkgoods.setBkgoodsseq(0);
			}
			if(null==bkgoods.getBkgoodsweight() || bkgoods.getBkgoodsweight().equals("")){
				bkgoods.setBkgoodsweight("0");
			}
			if(null==bkgoods.getBkgoodsnum() || bkgoods.getBkgoodsnum().equals("")){
				bkgoods.setBkgoodsnum(-1);
			}
			if(null==bkgoods.getBkgoodsallnum() || bkgoods.getBkgoodsallnum().equals("")){
				bkgoods.setBkgoodsallnum(-1);
			}
			if(null==bkgoods.getBkgoodsprice()){
				bkgoods.setBkgoodsprice(new Float(0.0));
			}
			LoginInfo info = (LoginInfo) request.getSession().getAttribute("loginInfo");	//登录信息
			bkgoods.setBkcreator(info.getUsername());			//创建人
			bkgoods.setBkcreatetime(DateUtils.getDateTime());		//创建时间
			bkgoods.setBkgoodsid(CommonUtil.getNewId());
			bkgoods.setBkgoodssurplus(bkgoods.getBkgoodsallnum());
			bkgoods.setBkgoodsstatue("启用");
			bkgoodsMapper.insertSelective(bkgoods);
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
	public Integer saveNewLargeCusGP(HttpSession session,Largecusprice newLargecusprice){
		Integer num = largecuspriceMapper.selectCusBargainByGoodsId(newLargecusprice);
		if(num>0){
			return 0;
		}
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");	//登录信息
		newLargecusprice.setLargecuspriceid(CommonUtil.getNewId());
		newLargecusprice.setLargecuspricecreatetime(DateUtils.getDateTime());	//创建时间
		newLargecusprice.setLargecuspricecreator(info.getUsername());		//创建人
		return largecuspriceMapper.insertSelective(newLargecusprice);
	}
	//修改录单客户商品价格
	@RequestMapping(value="/companySys/editLargeGoodsPrice")
	@ResponseBody
	public Integer editLargeGoodsPrice(HttpSession session,Largecusprice largecusprice){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");	//登录信息
		largecusprice.setLargecusupdtime(DateUtils.getDateTime());	//修改时间
		largecusprice.setLargecusupdor(info.getUsername());			//修改人
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
		cus.setCreatetime(datatime);	//创建时间
		cus.setCustomerstatue("启用");
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

















