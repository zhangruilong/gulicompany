package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantoutPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantout;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantoutAction extends WarrantoutAction {

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
					//如果状态修改为 “已发货” 则修改 “库存总账” 中对应商品的数量
					if(null != temp.getWarrantoutstatue() && temp.getWarrantoutstatue().equals("已发货")){
						String selGN = "";
						if(temp.getWarrantoutgtype().equals("商品")){
							selGN = "select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+
									"' and goodsnumstore='"+temp.getWarrantoutstore()+"'";
						} else {
							selGN = "select * from goodsnumview where goodscode='"+temp.getWarrantoutgcode()+
									"' and goodsname='"+temp.getWarrantoutgname()+"' and goodsunits='"+temp.getWarrantoutgunits()+
									"' and goodscompany='"+lgi.getCompanyid()+"' and goodsnumstore='"+temp.getWarrantoutstore()+"'";
						}
						List<Goodsnum> gnLi = selAll(Goodsnum.class,selGN);
						String updTemp = getUpdSingleSql(temp, WarrantoutPoco.KEYCOLUMN);
						if(gnLi.size()>0){
							Integer num =  Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
							String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
							String[] sqls = {updTemp,updNumSql};
							result = doAll(sqls);
						} else if(null!=type && type.equals("直接出库")){
							String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
									CommonUtil.getNewId()+"', '"+temp.getWarrantoutgoods()+"', '-"+temp.getWarrantoutnum()+"', '"+temp.getWarrantoutstore()+"')";
							String[] sqls = {updTemp,insNumSql};
							result = doAll(sqls);
						} else {
							result = "{success:true,code:401,msg:'未找到相关的“库存总账”信息,请问要出库么?'}";
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
		if(cuss.size()>0 && !cuss.get(0).getWarrantoutstatue().equals("已回滚")){
			Warrantout temp = cuss.get(0);
			//查询商品的库存总账
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+
					"' and goodsnumstore='"+temp.getWarrantoutstore()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) + Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String updTempSql = "update Warrantout set warrantoutstatue='已回滚' where idwarrantout='"+temp.getIdwarrantout()+"'";
				String[] sqls = {updNumSql,updTempSql};
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
			temp.setIdwarrantout(CommonUtil.getNewId());
			temp.setWarrantoutinswhen(DateUtils.getDateTime());
			temp.setWarrantoutinswho(lgi.getUsername());
			temp.setWarrantoutstatue("已发货");
			temp.setWarrantoutcompany(lgi.getCompanyid());
			if(CommonUtil.isEmpty(temp.getWarrantoutgtype())){
				temp.setWarrantoutgtype("商品");
			}
			
			String insSql = getInsSingleSql(temp);		//新增出库台账的sql
			String selGNSql = null;
			selGNSql = "select * from goodsnumview where goodscode='"+temp.getWarrantoutgcode()+"' and goodsname='"+temp.getWarrantoutgname()+
					"' and goodsunits='"+temp.getWarrantoutgunits()+"' and goodscompany='"+lgi.getCompanyid()+
					"' and goodsnumstore='"+temp.getWarrantoutstore()+"'";
			List<Goodsnum> gnLi = selAll(Goodsnum.class, selGNSql);
			if(gnLi.size()>0){
				Integer num =  Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String[] sqls = {insSql,updNumSql};
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
