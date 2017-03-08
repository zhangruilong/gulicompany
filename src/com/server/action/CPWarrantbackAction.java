package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantback;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantbackAction extends WarrantbackAction {

	//回滚
	public void backRollBack(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0 && !cuss.get(0).getWarrantbackstatue().equals("已回滚")){
			Warrantback temp = cuss.get(0);
			String updSql = "update Warrantback set warrantbackstatue='已回滚' where idwarrantback='"+temp.getIdwarrantback()+"'";
			//如果是完好退货就修改库存总账的数量
			if(temp.getWarrantbackstatue().equals("完好退货")){
				//根据 商品ID 和 仓库 查询商品的库存总账
				@SuppressWarnings("unchecked")
				List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantbackgoods()+
						"' and goodsnumstore='"+temp.getWarrantbackstore()+"'");
				if(gnLi.size()>0){
					Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantbacknum());
					String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
					String[] sqls = {updSql,updNumSql};
					result = doAll(sqls);
				} else {
					result = "{success:true,code:400,msg:'操作失败,未找到对应的库存总账信息'}";
				}
			} else if(temp.getWarrantbackstatue().equals("采购退货")){
				@SuppressWarnings("unchecked")
				List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantbackgoods()+
						"' and goodsnumstore='"+temp.getWarrantbackstore()+"'");
				if(gnLi.size()>0){
					Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) + Integer.parseInt(temp.getWarrantbacknum());
					String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
					String[] sqls = {updSql,updNumSql};
					result = doAll(sqls);
				} else {
					result = "{success:true,code:400,msg:'操作失败,未找到对应的库存总账信息'}";
				}
			} else {
				result = doSingle(updSql, null);
			}
		}
		responsePW(response, result);
	}
	
	//新增
	public void addWarrantback(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantback temp = cuss.get(0);
			String newid = CommonUtil.getNewId();
			temp.setIdwarrantback(newid);
			temp.setWarrantbackinswhen(DateUtils.getDateTime());
			temp.setWarrantbackinswho(lgi.getUsername());
			String insBackSql = getInsSingleSql(temp);
			if(temp.getWarrantbackstatue().equals("完好退货")){
				//完好退货
				@SuppressWarnings("unchecked")
				List<Goodsnum> gnLi = selAll(Goodsnum.class, "select * from goodsnum where goodsnumgoods='"+
						temp.getWarrantbackgoods()+"' and goodsnumstore='"+temp.getWarrantbackstore()+"'");
				if(gnLi.size()>0){
					Goodsnum gn = gnLi.get(0);
					gn.setGoodsnumnum((Integer.parseInt(gn.getGoodsnumnum())+Integer.parseInt(temp.getWarrantbacknum()))+"");
					String updGNSql = getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN);
					String[] sqls = {insBackSql,updGNSql};
					result = doAll(sqls);
				} else {
					String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
							newid+"', '"+temp.getWarrantbackgoods()+"', '"+temp.getWarrantbacknum()+"', '"+temp.getWarrantbackstore()+"')";
					String[] sqls = {insBackSql,insNumSql};
					result = doAll(sqls);
				}
			} else if(temp.getWarrantbackstatue().equals("采购退货")){
				//采购退货
				@SuppressWarnings("unchecked")
				List<Goodsnum> gnLi = selAll(Goodsnum.class, "select * from goodsnum where goodsnumgoods='"+
						temp.getWarrantbackgoods()+"' and goodsnumstore='"+temp.getWarrantbackstore()+"'");
				if(gnLi.size()>0){
					Goodsnum gn = gnLi.get(0);
					gn.setGoodsnumnum((Integer.parseInt(gn.getGoodsnumnum())-Integer.parseInt(temp.getWarrantbacknum()))+"");
					String updGNSql = getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN);
					String[] sqls = {insBackSql,updGNSql};
					result = doAll(sqls);
				} else {
					String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
							newid+"', '"+temp.getWarrantbackgoods()+"', '-"+temp.getWarrantbacknum()+"', '"+temp.getWarrantbackstore()+"')";
					String[] sqls = {insBackSql,insNumSql};
					result = doAll(sqls);
				}
			} else {
				result = doSingle(insBackSql, null);
			}
		}
		responsePW(response, result);
	}
}
