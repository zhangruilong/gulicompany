package com.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.pojo.Orderd;
import com.server.pojo.PrintInfo;
import com.system.action.System_tempruleAction;
import com.system.pojo.System_temprule;
import com.system.tools.CommonConst;

public class CPSystem_tempruleAction extends System_tempruleAction {
	
	@SuppressWarnings("unchecked")
	public void printOrder(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String ordermids = request.getParameter("ordermids");
		String[] odmids = ordermids.split(",");
		if(null !=odmids && odmids.length>0){
			String printInfoSQL = "select * from orderm om left join customer c on c.customerid = om.ordermcustomer left join company com "+
					"on com.companyid = om.ordermcompany where om.ordermstatue != '已删除' and ( ";
			for (int i = 0; i < odmids.length; i++) {
				printInfoSQL += "om.ordermid='"+odmids[i]+"' or ";
			}
			printInfoSQL = printInfoSQL.substring(0,printInfoSQL.length()-3)+") ";
			List<PrintInfo> printInfoLi = selAll(PrintInfo.class, printInfoSQL);		//查询 订单、客户、经销商 信息
			if(printInfoLi.size()>0){
				PrintInfo printInfo = printInfoLi.get(0);
				List<System_temprule> tempLi = selAll(System_temprule.class,				//查询模板
						"select * from system_temprule tp where tp.tempcode='"+printInfo.getOrdermcompany()+
						"' order by headno,startrow,startcol ");
				String queryOdLiSQL = "select * from orderd od where (";
				for (int i = 0; i < odmids.length; i++) {
					queryOdLiSQL += "od.orderdorderm='"+odmids[i]+"' or ";
				}
				queryOdLiSQL = queryOdLiSQL.substring(0,queryOdLiSQL.length()-3)+") order by orderdid desc";
				List<Orderd> odLi = selAll(Orderd.class, queryOdLiSQL);						//查询全部订单的订单商品
				String queryReOdsSQL = "select od.orderdcode,od.orderdtype,od.orderdunits,od.orderdprice from (select * from orderd where ";
				for (int i = 0; i < odmids.length; i++) {
					queryReOdsSQL += "orderd.orderdorderm='"+odmids[i]+"' or ";
				}
				queryReOdsSQL = queryReOdsSQL.substring(0,queryReOdsSQL.length()-3)+
						") od group by od.orderdcode,od.orderdtype,od.orderdunits,od.orderdprice having count(*) >1";
				List<Orderd> reOds = selAll(Orderd.class, queryOdLiSQL);	//查询重复的订单商品信息(编号/类型/规格/价格)
				if(null != reOds && reOds.size()>0){
					for (Orderd reOd : reOds) {
						Orderd newOd = null;									//合并后的订单商品
						ArrayList<Orderd> removeOdLi = new ArrayList<Orderd>();		//要移除的订单商品
						for (Orderd od : odLi) {
							if(od.getOrderdcode().equals(reOd.getOrderdcode()) && od.getOrderdtype().equals(reOd.getOrderdtype()) &&
									od.getOrderdunits().equals(reOd.getOrderdunits()) && od.getOrderdprice().equals(reOd.getOrderdprice())){
								if(null == newOd){
									newOd = od;
								} else {
									newOd.setOrderdnum(newOd.getOrderdnum() + od.getOrderdnum());
									newOd.setOrderdmoney(newOd.getOrderdmoney() + od.getOrderdmoney());
									newOd.setOrderdrightmoney(newOd.getOrderdrightmoney() + od.getOrderdrightmoney());
								}
								removeOdLi.add(od);
							}
						}
						odLi.removeAll(removeOdLi);			//移除掉重复的订单商品
						odLi.add(newOd);					//添加合并后的订单商品
					}
				}
				resultMap.put("success", true);
				resultMap.put("code", 400);
				resultMap.put("msg", "success");
				resultMap.put("tempList", tempLi);
				resultMap.put("printinfo", printInfoLi);
				resultMap.put("orderdList", odLi);
				
				result = CommonConst.GSON.toJson(resultMap);
			} else {
				result = "{success:true,code:400,msg:'打印失败,没有查询到该订单。'}";
			}
		}
		responsePW(response, result);
	}

}
