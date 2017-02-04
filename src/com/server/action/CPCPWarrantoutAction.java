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

public class CPCPWarrantoutAction extends WarrantoutAction {

	//删除入库台账
	@SuppressWarnings("unchecked")
	public void delWarrantout(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantout temp:cuss){
			//查询商品的库存总账
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) + Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String delTempSql = "delete from Warrantout where idwarrantout='"+temp.getIdwarrantout()+"'";
				String[] sqls = {updNumSql,delTempSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
	//修改出库台账
	@SuppressWarnings("unchecked")
	public void updWarrantout(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantout temp:cuss){
			//查询修改前的入库台账记录
			List<Warrantout> waLi = selAll(Warrantout.class, "select * from Warrantout w where w.idwarrantout='"+temp.getIdwarrantout()+"'", "mysql");
			if(waLi.size()>0){
				//计算修改后的差值
				Integer diffNum = Integer.parseInt(waLi.get(0).getWarrantoutnum()) - Integer.parseInt(temp.getWarrantoutnum());
				//查询商品的库存总账
				List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+"'");
				if(gnLi.size()>0){
					Goodsnum gn = gnLi.get(0);
					Integer currNum = Integer.parseInt(gn.getGoodsnumnum()) + diffNum;
					gn.setGoodsnumnum(currNum.toString());
					String updGNSql = getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN);
					temp.setWarrantoutupdwhen(DateUtils.getDateTime());
					temp.setWarrantoutupdwho(lgi.getUsername());
					String tempSql = getUpdSingleSql(temp, WarrantoutPoco.KEYCOLUMN);
					String[] sqls = {tempSql,updGNSql};
					result = doAll(sqls);
				}
			}
		}
		responsePW(response, result);
	}
	//新增出库台账
	@SuppressWarnings("unchecked")
	public void insWarrantout(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Warrantout temp:cuss){
			if(CommonUtil.isNull(temp.getIdwarrantout())){
				temp.setIdwarrantout(CommonUtil.getNewId());
				temp.setWarrantoutinswhen(DateUtils.getDateTime());
				temp.setWarrantoutinswho(lgi.getUsername());
			}
			String insSql = getInsSingleSql(temp);		//新增入库台账的sql
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantoutgoods()+"'");
			if(CommonUtil.isNotEmpty(gnLi)){
				Integer num =  Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String[] sqls = {insSql,updNumSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
}
