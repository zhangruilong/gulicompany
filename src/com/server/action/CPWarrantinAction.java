package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantinPoco;
import com.server.pojo.Goods;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantin;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantinAction extends WarrantinAction {
	
	//新商品入库
	public void warrantinNewGoo(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		List<Goods> gooLi = new ArrayList<Goods>();
		if(CommonUtil.isNotEmpty(json)){
			gooLi = CommonConst.GSON.fromJson(json,  new TypeToken<ArrayList<Goods>>() {}.getType());
			cuss = CommonConst.GSON.fromJson(json, TYPE);
		} 
		if(gooLi.size()>0 && cuss.size()>0){
			Goods addGoods = gooLi.get(0);
			Warrantin temp = cuss.get(0);
			if(null==addGoods.getGoodsweight()||addGoods.getGoodsweight().equals("")||addGoods.getGoodsweight().equals("undefined")){
				addGoods.setGoodsweight("0");
			}
			if(null==addGoods.getGoodsorder()){
				addGoods.setGoodsorder(0);
			}
			addGoods.setGoodsstatue("下架");
			addGoods.setCreatetime(DateUtils.getDateTime());		//创建时间
			addGoods.setCreator(lInfo.getUsername());			//创建人
			addGoods.setGoodscompany(lInfo.getCompanyid());		//经销商ID
			String newid = CommonUtil.getNewId();
			addGoods.setGoodsid(newid);				//商品ID
			String addGooSql = getInsSingleSql(addGoods);	//新增商品的sql
			
			//新增库存总账记录
			Goodsnum gn = new Goodsnum();
			gn.setIdgoodsnum(newid);
			gn.setGoodsnumnum(temp.getWarrantinnum());
			gn.setGoodsnumstore(temp.getWarrantinstore());
			gn.setGoodsnumgoods(newid);				//商品ID
			String addGNSql = getInsSingleSql(gn);			//新增库存总账的sql
			
			//新增入库台账
			temp.setIdwarrantin(newid);
			temp.setWarrantingoods(newid);
			temp.setWarrantininswhen(DateUtils.getDateTime());
			temp.setWarrantininswho(lInfo.getUsername());
			String addInSql = getInsSingleSql(temp);		//新增入库台账的sql
			String[] sqls = {addGooSql,addGNSql,addInSql};
			result = doAll(sqls);
		}
		responsePW(response, result);
	}
	//回滚入库台账
	@SuppressWarnings("unchecked")
	public void warrantinRollBACK(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0 && (cuss.get(0).getWarrantinstatue()==null || !cuss.get(0).getWarrantinstatue().equals("已回滚"))){
			Warrantin temp = cuss.get(0);
			//查询商品的库存总账
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+
					"' and goodsnumstore='"+temp.getWarrantinstore()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantinnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String delTempSql = "update Warrantin set warrantinstatue='已回滚' where idwarrantin='"+temp.getIdwarrantin()+"'";
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
		if(cuss.size()>0){
			Warrantin temp = cuss.get(0);
			String newid = CommonUtil.getNewId();
			if(CommonUtil.isNull(temp.getIdwarrantin())){
				temp.setIdwarrantin(newid);
				temp.setWarrantininswhen(DateUtils.getDateTime());
				temp.setWarrantininswho(lgi.getUsername());
			}
			String insSql = getInsSingleSql(temp);		//新增入库台账的sql
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+
					"' and goodsnumstore='"+temp.getWarrantinstore()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(temp.getWarrantinnum()) + Integer.parseInt(gnLi.get(0).getGoodsnumnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String[] sqls = {insSql,updNumSql};
				result = doAll(sqls);
			} else {
				String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
						newid+"', '"+temp.getWarrantingoods()+"', '"+temp.getWarrantinnum()+"', '"+temp.getWarrantinstore()+"')";
				String[] sqls = {insSql,insNumSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
}
