package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantinPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantin;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantinAction extends WarrantinAction {

	//删除入库台账
	@SuppressWarnings("unchecked")
	public void delWarrantin(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantin temp:cuss){
			//查询商品的库存总账
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantinnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String delTempSql = "delete from Warrantin where idwarrantin='"+temp.getIdwarrantin()+"'";
				String[] sqls = {updNumSql,delTempSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
	//新增入库台账
	@SuppressWarnings("unchecked")
	public void addWarrantin(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantin temp:cuss){
			if(CommonUtil.isNull(temp.getIdwarrantin())){
				temp.setIdwarrantin(CommonUtil.getNewId());
				temp.setWarrantininswhen(DateUtils.getDateTime());
				temp.setWarrantininswho(lgi.getUsername());
			}
			String insSql = getInsSingleSql(temp);		//新增入库台账的sql
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+"'");
			if(CommonUtil.isNotEmpty(gnLi)){
				Integer num = Integer.parseInt(temp.getWarrantinnum()) + Integer.parseInt(gnLi.get(0).getGoodsnumnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String[] sqls = {insSql,updNumSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
	//修改入库台账
	@SuppressWarnings("unchecked")
	public void updWarrantin(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantin temp:cuss){
			//查询修改前的入库台账记录
			List<Warrantin> waLi = selAll(Warrantin.class, "select * from Warrantin w where w.idwarrantin='"+temp.getIdwarrantin()+"'", "mysql");
			if(waLi.size()>0){
				//计算修改后的差值
				Integer diffNum = Integer.parseInt(temp.getWarrantinnum()) - Integer.parseInt(waLi.get(0).getWarrantinnum());
				//查询商品的库存总账
				List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+"'");
				if(gnLi.size()>0){
					Goodsnum gn = gnLi.get(0);
					Integer currNum = Integer.parseInt(gn.getGoodsnumnum()) + diffNum;
					gn.setGoodsnumnum(currNum.toString());
					String updGNSql = getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN);
					temp.setWarrantinupdwhen(DateUtils.getDateTime());
					temp.setWarrantinupdwho(lgi.getUsername());
					String tempSql = getUpdSingleSql(temp, WarrantinPoco.KEYCOLUMN);
					String[] sqls = {tempSql,updGNSql};
					result = doAll(sqls);
				}
			}
		}
		responsePW(response, result);
	}
}
