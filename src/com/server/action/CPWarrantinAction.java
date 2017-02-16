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
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+
					"' and goodsnumstore='"+temp.getWarrantinstore()+"'");
			if(gnLi.size()>0){
				Integer num = Integer.parseInt(gnLi.get(0).getGoodsnumnum()) - Integer.parseInt(temp.getWarrantinnum());
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+num+"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String delTempSql = "update Warrantin set warrantinstatue='回滚' where idwarrantin='"+temp.getIdwarrantin()+"'";
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
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantingoods()+"'");
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
