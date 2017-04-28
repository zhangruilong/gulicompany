package com.server.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantoutPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.Goodsnumview;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantout;
import com.server.pojo.Warrantoutview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantoutAction extends WarrantoutAction {

	//手工单
	public void manualInsAll(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String goodsnumjson = request.getParameter("goodsnumjson");
		System.out.println("json : " + json);
		System.out.println("goodsnumjson : "+goodsnumjson); 
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
//		List<Goodsnum> gnLi = new ArrayList<Goodsnum>();
//		if(CommonUtil.isNotEmpty(goodsnumjson)){
//			gnLi = CommonConst.GSON.fromJson(goodsnumjson, new TypeToken<ArrayList<Goodsnum>>() {}.getType());
//		}
		List<String> sqlLi = new ArrayList<String>();
		String time = DateUtils.getDateTime();
		for (int i = 0; i < cuss.size(); i++) {
			Warrantout temp = cuss.get(i);
//			Goodsnum gn = gnLi.get(i);
			temp.setIdwarrantout(CommonUtil.getNewId());
			temp.setWarrantoutinswhen(time);
			temp.setWarrantoutinswho(lgi.getUsername());
			temp.setWarrantoutupdwhen(time);
			temp.setWarrantoutupdwho(lgi.getUsername());
			temp.setWarrantoutgunit("");
			temp.setWarrantoutwho("");
//			Integer num = Integer.parseInt(gn.getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
//			gn.setGoodsnumnum(num.toString());
//			sqlLi.add(getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN));
			sqlLi.add(getInsSingleSql(temp));
		}
		result = doAll(sqlLi);
		responsePW(response, result);
	}
	
	//出库单打印次数
	@SuppressWarnings("unchecked")
	public void updPrintTimes(HttpServletRequest request, HttpServletResponse response){
		String ordermid = request.getParameter("ordermid");
		List<String> sqlLi = new ArrayList<String>();
		if(!CommonUtil.isNull(ordermid)){
			List<Warrantout> woLi = selAll(Warrantout.class, "select * from Warrantout where warrantoutodm='"+ordermid+"'");
			if(woLi.size()>0){
				for (Warrantout wo : woLi) {
					//修改打印次数
					if(!CommonUtil.isNull(wo.getWarrantoutprint())){
						Integer outNum = Integer.parseInt(wo.getWarrantoutprint())+1;
						wo.setWarrantoutprint(outNum.toString());
					} else {
						wo.setWarrantoutprint("1");
					}
					sqlLi.add(getUpdSingleSql(wo, WarrantoutPoco.KEYCOLUMN));
				}
				result = doAll(sqlLi);
			}
		}
		responsePW(response, result);
	}
	
	//出库单打印
	@SuppressWarnings("unchecked")
	public void outPrint(HttpServletRequest request, HttpServletResponse response){
		String ordermid = request.getParameter("ordermid");
		String idwarrantouts = request.getParameter("idwarrantouts");
		if(!CommonUtil.isNull(ordermid)){
			List<Warrantoutview> outLi = selAll(Warrantoutview.class, "select * from Warrantoutview where warrantoutodm='"+ordermid+"'");
			if(!CommonUtil.isNull(idwarrantouts)){
				String[] outIds = idwarrantouts.split(",");
				for (String outId : outIds) {
					outLi.addAll(selAll(Warrantoutview.class, "select * from Warrantoutview where idwarrantout='"+outId+"'"));
				}
			}
			Pageinfo pageinfo = new Pageinfo(0, outLi);
			result = CommonConst.GSON.toJson(pageinfo);
		}
		responsePW(response, result);
	}
	
	//修改出库台账
	@SuppressWarnings("unchecked")
	public void updWarrantout(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String type = request.getParameter("type");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantout temp = cuss.get(0);
			//查询修改前的 “出库台账”
			List<Warrantout> odTempLi = selAll(Warrantout.class, "select * from Warrantout where idwarrantout='"+temp.getIdwarrantout()+"'");
			if(odTempLi.size()>0){
				Warrantout odTemp = odTempLi.get(0);
				//修改前状态是 “发货请求” 才可以修改
				if(null != odTemp.getWarrantoutstatue() && odTemp.getWarrantoutstatue().equals("发货请求")){
					temp.setWarrantoutupdwhen(DateUtils.getDateTime());
					temp.setWarrantoutupdwho(lgi.getUsername());
					String updTemp = getUpdSingleSql(temp, WarrantoutPoco.KEYCOLUMN);	//修改出库台账的SQL
					//如果状态修改为 “已发货” 则修改 “库存总账” 中对应商品的数量
					if(null != temp.getWarrantoutstatue() && temp.getWarrantoutstatue().equals("已发货")){
						String selGN = "";
						if(temp.getWarrantoutgtype().equals("商品")){
							selGN = "select * from goods g left join goodsnum gn on gn.goodsnumgoods=g.goodsid where g.goodsid='"+temp.getWarrantoutgoods()+
									"' and (gn.goodsnumstore='"+temp.getWarrantoutstore()+"' or gn.goodsnumstore is null)";
						} else {
							selGN = "select * from goods g left join goodsnum gn on gn.goodsnumgoods=g.goodsid where g.goodscode='"+temp.getWarrantoutgcode()+
									"' and g.goodsunits='"+temp.getWarrantoutgunits()+
									"' and g.goodscompany='"+lgi.getCompanyid()+"' and (gn.goodsnumstore='"+temp.getWarrantoutstore()+"' or gn.goodsnumstore is null )";
						}
						List<Goodsnumview> gnLi = selAll(Goodsnumview.class,selGN);
						
						Integer woNum = getTotal("select count(*) from warrantout wo where wo.warrantoutstatue='发货请求' and wo.warrantoutodm='"+temp.getWarrantoutodm()+"'");
						String updOrdermSQL = null;		//修改订单总表状态的SQL
						if(!CommonUtil.isNull(temp.getWarrantoutodm()) && woNum==1){
							updOrdermSQL = "update orderm set ordermstatue='已发货' where ordermid='"+temp.getWarrantoutodm()+"'";
						}
						if(gnLi.size()>0 && null != gnLi.get(0).getIdgoodsnum()){
							Integer num =  Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
							String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
							List<String> sqls = null;
							if(null != updOrdermSQL){
								sqls = Arrays.asList(updTemp,updNumSql,updOrdermSQL);
							} else {
								sqls = Arrays.asList(updTemp,updNumSql);
							}
							result = doAll(sqls);
						} else if(null!=type && type.equals("直接出库")){
							if(gnLi.size()>0 && null != gnLi.get(0).getGoodsid()){
								String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
										CommonUtil.getNewId()+"', '"+gnLi.get(0).getGoodsid()+"', '-"+temp.getWarrantoutnum()+"', '"+temp.getWarrantoutstore()+"')";
								List<String> sqls = null;
								if(null != updOrdermSQL){
									sqls = Arrays.asList(updTemp,insNumSql,updOrdermSQL);
								} else {
									sqls = Arrays.asList(updTemp,insNumSql);
								}
								result = doAll(sqls);
							} else {
								List<String> sqls = null;
								if(null != updOrdermSQL){
									sqls = Arrays.asList(updTemp,updOrdermSQL);
								} else {
									sqls = Arrays.asList(updTemp);
								}
								result = doAll(sqls);
							}
						} else {
							result = "{success:true,code:401,msg:'未找到相关的“库存总账”信息,请问要出库么?'}";
						}
					} else if(null != temp.getWarrantoutstatue() && temp.getWarrantoutstatue().equals("另行处理")){
						Integer woNum = getTotal("select count(*) from warrantout wo where wo.warrantoutstatue='发货请求' and wo.warrantoutodm='"
										+temp.getWarrantoutodm()+"'");
						if(!CommonUtil.isNull(temp.getWarrantoutodm()) && woNum==1){
							String updOrdermSQL = "update orderm set ordermstatue='已发货' where ordermid='"+temp.getWarrantoutodm()+"'";
							List<String> sqls = Arrays.asList(updTemp,updOrdermSQL);
							result = doAll(sqls);
						} else {
							result = doSingle(updTemp, null);
						}
						
					} else {
						result = updSingle(temp, WarrantoutPoco.KEYCOLUMN);
					}
				} else {
					result = "{success:true,code:400,msg:'无法修改已发货的订单。'}";
				}
			}
		}
		responsePW(response, result);
	}
	//回滚出库台账
	@SuppressWarnings("unchecked")
	public void delWarrantout(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0 && (cuss.get(0).getWarrantoutstatue()==null || !cuss.get(0).getWarrantoutstatue().equals("已回滚"))){
			Warrantout temp = cuss.get(0);
			//查询商品的库存总账
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+
					"' and goodsnumstore='"+temp.getWarrantoutstore()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) + Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String updTempSql = "update Warrantout set warrantoutstatue='已回滚' where idwarrantout='"+temp.getIdwarrantout()+"'";
				List<String> sqls = Arrays.asList(updNumSql,updTempSql);
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
	//新增出库台账
	@SuppressWarnings("unchecked")
	public void insWarrantout(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String type = request.getParameter("type");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantout temp = cuss.get(0);
			String cTime = DateUtils.getDateTime();
			temp.setIdwarrantout(CommonUtil.getNewId());
			temp.setWarrantoutinswhen(cTime);			//创建时间
			temp.setWarrantoutinswho(lgi.getUsername());
			temp.setWarrantoutupdwhen(cTime);			//修改时间
			temp.setWarrantoutupdwho(lgi.getUsername());
			temp.setWarrantoutstatue("已发货");
			temp.setWarrantoutcompany(lgi.getCompanyid());
			temp.setWarrantoutprint("0");
			if(CommonUtil.isEmpty(temp.getWarrantoutgtype())){
				temp.setWarrantoutgtype("商品");
			}
			
			String insSql = getInsSingleSql(temp);		//新增出库台账的sql
			String selGNSql = null;
			selGNSql = "select * from goodsnumview where goodscode='"+temp.getWarrantoutgcode()+"' and goodsunits='"+temp.getWarrantoutgunits()+
					"' and goodscompany='"+lgi.getCompanyid()+"' and goodsnumstore='"+temp.getWarrantoutstore()+"'";
			List<Goodsnum> gnLi = selAll(Goodsnum.class, selGNSql);
			if(gnLi.size()>0){
				Integer num =  Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				List<String> sqls = Arrays.asList(insSql,updNumSql);
				result = doAll(sqls);
			} else if(null!=type && type.equals("直接出库")){
				result = doSingle(insSql, null);
			} else {
				result = "{success:true,code:401,msg:'未找到相应的“库存总账”记录'}";
			}
		}
		responsePW(response, result);
	}
}
