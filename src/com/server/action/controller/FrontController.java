package com.server.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.AddressMapper;
import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.GivegoodsMapper;
import com.server.dao.mapper.GoodsMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.dao.mapper.TimegoodsMapper;
import com.server.pojo.entity.Address;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Givegoods;
import com.server.pojo.entity.GoodsVo;
import com.server.pojo.entity.Orderd;
import com.system.tools.util.DateUtils;
/**
 * 首页
 * @author taolichao
 *
 */
@Controller
public class FrontController {
	@Autowired
	private CityMapper cityMapper;	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private OrdermMapper ordermMapper;
	@Autowired
	private GivegoodsMapper givegoodsMapper;
	@Autowired
	private OrderdMapper orderdMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private TimegoodsMapper timegoodsMapper;
	//查询首页所需的数据
	@RequestMapping(value="/guliwang/doGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		List<City> cities = null;
		if(cityMapper.selectByCitynameOrKey(parentCity) != null && cityMapper.selectByCitynameOrKey(parentCity).size() > 0){
			parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
			cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		} else {
			cities = cityMapper.selectByCityparent("16");	//根据父类城市ID查询得到地区集合
		}
		pageInfo.put("cityList", cities);
		pageInfo.put("parentCity", parentCity);
		pageInfo.put("companyCondition", companyCondition);
		return pageInfo;
	}
	
	//秒杀页
	@RequestMapping(value="/guliwang/miaoshaPage",produces="application/json")
	@ResponseBody
	public Map<String, Object> miaoshaPage(Company companyCondition){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '秒杀商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		return pageInfo;
	}
	//买赠页
	@RequestMapping(value="/guliwang/maizengPage",produces="application/json")
	@ResponseBody
	public Map<String, Object> maizengPage(Givegoods givegoods){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '买赠商品'
		List<Givegoods> giveList = givegoodsMapper.selectByCompanyXian(givegoods);
		pageInfo.put("giveList", giveList);
		return pageInfo;
	}
	//判断秒杀商品的限购数量是否到达上限
	@RequestMapping(value="/guliwang/judgePurchase",produces="application/json")
	@ResponseBody
	public Map<String, Object> judgePurchase(Integer timegoodsnum,String timegoodscode,String customerid){
		Map<String, Object> map = new HashMap<String, Object>();
		String date = DateUtils.getDate();
		Integer num = ordermMapper.selectOrderByCustomerGoods(customerid, timegoodscode,"秒杀",date + " 00:00:00",date + " 23:59:59");
		if(num != null && num >= timegoodsnum){
			map.put("result", "no");
		} else {
			map.put("result", "ok");
		}
		return map;
	}
	//判断买赠商品的限购数量
	@RequestMapping(value="/guliwang/judgePurchaseGiveGoods")
	@ResponseBody
	public Map<String, Object> judgePurchaseGiveGoods(Integer givegoodsnum,String givegoodscode,String customerid){
		Map<String, Object> map = new HashMap<String, Object>();
		String date = DateUtils.getDate();
		Integer num = ordermMapper.selectOrderByCustomerGoods(customerid, givegoodscode,"买赠",date + " 00:00:00",date + " 23:59:59");
		if(num != null && num >= givegoodsnum){
			map.put("result", "no");
		} else {
			map.put("result", "ok");
		}
		return map;
	}
	//判断限购数量是否到达上限
	@RequestMapping(value="/guliwang/queryCusSecKillOrderd",produces="application/json")
	@ResponseBody
	public Map<String, Object> queryCusSecKillOrderd(Orderd orderd){
		Map<String, Object> map = new HashMap<String, Object>();
		Address record = new Address();
		String msg = "";
		List<Address> addressList = null;
		if(orderd != null && orderd.getOrderm() != null && orderd.getOrderm().getOrdermcustomer() != null){
			record.setAddresscustomer(orderd.getOrderm().getOrdermcustomer());
			addressList = addressMapper.selectDefAddress(record);
		}
		if(addressList == null || addressList.size() == 0){
			msg = "no";
		}
		String date = DateUtils.getDate();
		orderd.setOrderdtype("秒杀");
		List<Orderd> miaoshaList = orderdMapper.selectOrderdByCustomerMiaosha(orderd,date + " 00:00:00",date + " 23:59:59");
		orderd.setOrderdtype("买赠");
		List<Orderd> giveGoodsList = orderdMapper.selectOrderdByCustomerMiaosha(orderd,date + " 00:00:00",date + " 23:59:59");
		map.put("msg", msg);
		map.put("miaoshaList", miaoshaList);
		map.put("giveGoodsList", giveGoodsList);
		return map;
	}
	//热销商品
	@RequestMapping(value="/guliwang/hotTodayGoods")
	@ResponseBody
	public List<GoodsVo> hotTodayGoods(String cityname,String staTime,String endTime,String customertype,String customerlevel){
		List<Orderd> orderdList = orderdMapper.selectHotGoodsCodeAndType(staTime, endTime,cityname);
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		for (Orderd orderd : orderdList) {
			if(orderd.getOrderdtype().equals("商品")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("商品");
				if(goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel) != null && goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel).size() >0){
					goodsVo.setGoods(goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel).get(0));
					goodsVoList.add(goodsVo);
				}
			} else if(orderd.getOrderdtype().equals("秒杀")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("秒杀");
				if(timegoodsMapper.selectByCode(orderd.getOrderdcode()) != null && timegoodsMapper.selectByCode(orderd.getOrderdcode()).size() >0){
					goodsVo.setTimegoods(timegoodsMapper.selectByCode(orderd.getOrderdcode()).get(0));
					goodsVoList.add(goodsVo);
				}
			} else {
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("买赠");
				if(givegoodsMapper.selectByCode(orderd.getOrderdcode()) != null && givegoodsMapper.selectByCode(orderd.getOrderdcode()).size() >0){
					goodsVo.setGivegoods(givegoodsMapper.selectByCode(orderd.getOrderdcode()).get(0));
					goodsVoList.add(goodsVo);
				}
			}
		}
		return goodsVoList;
	}
	//根据订单详情得到订单商品
	@RequestMapping(value="/guliwang/queryREgoumaiGoods")
	@ResponseBody
	public List<GoodsVo> queryREgoumaiGoods(String orderdcodes,String orderdtypes,String customertype,String customerlevel){
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		String[] orderdcodeArr = orderdcodes.split(",");
		String[] orderdtypeArr = orderdtypes.split(",");
		for (int i = 0; i < orderdtypeArr.length; i++) {
			if(orderdtypeArr[i].equals("商品")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("商品");
				if(goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel) != null && goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel).size() >0){
					goodsVo.setGoods(goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel).get(0));
					goodsVoList.add(goodsVo);
				}
			} else if(orderdtypeArr[i].equals("秒杀")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("秒杀");
				if(timegoodsMapper.selectByCode(orderdcodeArr[i]) != null && timegoodsMapper.selectByCode(orderdcodeArr[i]).size() >0){
					goodsVo.setTimegoods(timegoodsMapper.selectByCode(orderdcodeArr[i]).get(0));
					goodsVoList.add(goodsVo);
				}
			} else if(orderdtypeArr[i].equals("买赠")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("买赠");
				if(givegoodsMapper.selectByCode(orderdcodeArr[i]) != null && givegoodsMapper.selectByCode(orderdcodeArr[i]).size() >0){
					goodsVo.setGivegoods(givegoodsMapper.selectByCode(orderdcodeArr[i]).get(0));
					goodsVoList.add(goodsVo);
				}
			}
		}
		return goodsVoList;
	}
////////////////////////////////////////////////// EMP补单页面  /////////////////////////////////////////////////////////////////////////////////
	
	//查询首页所需的数据( EMP )
	@RequestMapping(value="/guliwangemp/doEmpGuliwangIndex",produces="application/json")
	@ResponseBody
	public Map<String, Object> doEmpGuliwangIndex(Company companyCondition,City parentCity){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		parentCity = cityMapper.selectByCitynameOrKey(parentCity).get(0);				//根据父类城市名称或主键查询得到父类城市
		List<City> cities = cityMapper.selectByCityparent(parentCity.getCityid());	//根据父类城市ID查询得到地区集合
		pageInfo.put("cityList", cities);
		pageInfo.put("parentCity", parentCity);
		pageInfo.put("companyCondition", companyCondition);
		return pageInfo;
	}
	//秒杀页
	@RequestMapping(value="/guliwangemp/miaoshaPageEmp",produces="application/json")
	@ResponseBody
	public Map<String, Object> miaoshaEmpPage(Company companyCondition){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '秒杀商品'
		List<Company> companyList = companyMapper.selectCompanyByCondition(companyCondition);	//条件中包含城市名
		pageInfo.put("companyList", companyList);
		return pageInfo;
	}
	//买赠页
	@RequestMapping(value="/guliwangemp/maizengPageEmp",produces="application/json")
	@ResponseBody
	public Map<String, Object> maizengEmpPage(Givegoods givegoods){
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		//根据 '地区' 查询该地区的 '经销商' 和该经销商的 '买赠商品'
		List<Givegoods> giveList = givegoodsMapper.selectByCompanyXian(givegoods);
		pageInfo.put("giveList", giveList);
		return pageInfo;
	}
	//判断秒杀商品的限购数量是否到达上限
	@RequestMapping(value="/guliwangemp/judgePurchaseEmp",produces="application/json")
	@ResponseBody
	public Map<String, Object> judgePurchaseEmp(Integer timegoodsnum,String timegoodscode,String customerid){
		Map<String, Object> map = new HashMap<String, Object>();
		String date = DateUtils.getDate();
		Integer num = ordermMapper.selectOrderByCustomerGoods(customerid, timegoodscode,"秒杀",date + " 00:00:00",date + " 23:59:59");
		if(num != null && num >= timegoodsnum){
			map.put("result", "no");
		} else {
			map.put("result", "ok");
		}
		return map;
	}
	//判断买赠商品的限购数量
	@RequestMapping(value="/guliwangemp/judgePurchaseGiveGoodsEmp")
	@ResponseBody
	public Map<String, Object> judgePurchaseGiveGoodsEmp(Integer givegoodsnum,String givegoodscode,String customerid){
		Map<String, Object> map = new HashMap<String, Object>();
		String date = DateUtils.getDate();
		Integer num = ordermMapper.selectOrderByCustomerGoods(customerid, givegoodscode,"买赠",date + " 00:00:00",date + " 23:59:59");
		if(num != null && num >= givegoodsnum){
			map.put("result", "no");
		} else {
			map.put("result", "ok");
		}
		return map;
	}
	//判断限购数量是否到达上限
	@RequestMapping(value="/guliwangemp/queryCusSecKillOrderdEmp",produces="application/json")
	@ResponseBody
	public Map<String, Object> queryCusSecKillOrderdEmp(Orderd orderd){
		Map<String, Object> map = new HashMap<String, Object>();
		Address record = new Address();
		String msg = "";
		record.setAddresscustomer(orderd.getOrderm().getOrdermcustomer());
		List<Address> addressList = addressMapper.selectDefAddress(record);
		if(addressList == null || addressList.size() == 0){
			msg = "no";
		}
		String date = DateUtils.getDate();
		orderd.setOrderdtype("秒杀");
		List<Orderd> miaoshaList = orderdMapper.selectOrderdByCustomerMiaosha(orderd,date + " 00:00:00",date + " 23:59:59");
		orderd.setOrderdtype("买赠");
		List<Orderd> giveGoodsList = orderdMapper.selectOrderdByCustomerMiaosha(orderd,date + " 00:00:00",date + " 23:59:59");
		map.put("msg", msg);
		map.put("miaoshaList", miaoshaList);
		map.put("giveGoodsList", giveGoodsList);
		return map;
	}
	//热销商品
	@RequestMapping(value="/guliwangemp/hotTodayGoodsEmp")
	@ResponseBody
	public List<GoodsVo> hotTodayGoodsEmp(String cityname,String staTime,String endTime,String customertype,String customerlevel){
		List<Orderd> orderdList = orderdMapper.selectHotGoodsCodeAndType(staTime, endTime,cityname);
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		for (Orderd orderd : orderdList) {
			if(orderd.getOrderdtype().equals("商品")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("商品");
				if(goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel) != null && goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel).size() >0){
					goodsVo.setGoods(goodsMapper.selectByGoods(orderd.getOrderdcode(),customertype,customerlevel).get(0));
					goodsVoList.add(goodsVo);
				}
			} else if(orderd.getOrderdtype().equals("秒杀")){
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("秒杀");
				if(timegoodsMapper.selectByCode(orderd.getOrderdcode()) != null && timegoodsMapper.selectByCode(orderd.getOrderdcode()).size() >0){
					goodsVo.setTimegoods(timegoodsMapper.selectByCode(orderd.getOrderdcode()).get(0));
					goodsVoList.add(goodsVo);
				}
			} else {
				GoodsVo goodsVo = new GoodsVo();
				goodsVo.setType("买赠");
				if(givegoodsMapper.selectByCode(orderd.getOrderdcode()) != null && givegoodsMapper.selectByCode(orderd.getOrderdcode()).size() >0){
					goodsVo.setGivegoods(givegoodsMapper.selectByCode(orderd.getOrderdcode()).get(0));
					goodsVoList.add(goodsVo);
				}
			}
		}
		return goodsVoList;
	}
	//根据订单详情得到订单商品
		@RequestMapping(value="/guliwang/queryREgoumaiGoodsEmp")
		@ResponseBody
		public List<GoodsVo> queryREgoumaiGoodsEmp(String orderdcodes,String orderdtypes,String customertype,String customerlevel){
			List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
			String[] orderdcodeArr = orderdcodes.split(",");
			String[] orderdtypeArr = orderdtypes.split(",");
			for (int i = 0; i < orderdtypeArr.length; i++) {
				if(orderdtypeArr[i].equals("商品")){
					GoodsVo goodsVo = new GoodsVo();
					goodsVo.setType("商品");
					if(goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel) != null && goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel).size() >0){
						goodsVo.setGoods(goodsMapper.selectByGoods(orderdcodeArr[i],customertype,customerlevel).get(0));
						goodsVoList.add(goodsVo);
					}
				} else if(orderdtypeArr[i].equals("秒杀")){
					GoodsVo goodsVo = new GoodsVo();
					goodsVo.setType("秒杀");
					if(timegoodsMapper.selectByCode(orderdcodeArr[i]) != null && timegoodsMapper.selectByCode(orderdcodeArr[i]).size() >0){
						goodsVo.setTimegoods(timegoodsMapper.selectByCode(orderdcodeArr[i]).get(0));
						goodsVoList.add(goodsVo);
					}
				} else if(orderdtypeArr[i].equals("买赠")){
					GoodsVo goodsVo = new GoodsVo();
					goodsVo.setType("买赠");
					if(givegoodsMapper.selectByCode(orderdcodeArr[i]) != null && givegoodsMapper.selectByCode(orderdcodeArr[i]).size() >0){
						goodsVo.setGivegoods(givegoodsMapper.selectByCode(orderdcodeArr[i]).get(0));
						goodsVoList.add(goodsVo);
					}
				}
			}
			return goodsVoList;
		}
	/*//商品详情
	@RequestMapping(value="/guliwang/goodsDetail")
	@ResponseBody
	public Goods goodsDetail(Goods detGoods){
		detGoods = goodsMapper.selectByPrimaryKey(detGoods.getGoodsid());
		return detGoods;
	}
	//买赠商品详情
	@RequestMapping(value="/guliwang/giveGoodsDetail")
	@ResponseBody
	public Givegoods giveGoodsDetail(Givegoods detGoods){
		detGoods = givegoodsMapper.selectByPrimaryKey(detGoods.getGivegoodsid());
		return detGoods;
	}
	//秒杀商品详情
	@RequestMapping(value="/guliwang/timeGoodsDetail")
	@ResponseBody
	public Timegoods timeGoodsDetail(Timegoods detGoods){
		detGoods = timegoodsMapper.selectByPrimaryKey(detGoods.getTimegoodsid());
		return detGoods;
	}*/
}











