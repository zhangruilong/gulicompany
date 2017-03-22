package com.server.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.BkgoodsPoco;
import com.server.poco.OrdermPoco;
import com.server.pojo.Bkgoods;
import com.server.pojo.Orderd;
import com.server.pojo.Orderm;
import com.server.pojo.OutOrder;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

public class CPOrdermAction extends OrdermAction {
	
	//导出 出库单
	@SuppressWarnings("unchecked")
	public void expChukudan(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] queryfieldname = {
				"ordermcode",
				"customershop",
				"customername",
				"customerphone",
				"warrantoutupdwhen"
		};
		String companyid = request.getParameter("companyid");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String query = request.getParameter("query");
		if(!CommonUtil.isNull(query)){
			query = " and ("+getQuerysql(query, queryfieldname)+")";
		} else {
			query = "";
		}
		String selSQL = "(select '' idwarrantout,wo.warrantoutodm,om.ordermcode,om.ordermnum,c.customershop,c.customername,c.customerphone,c.customerid,max(wo.warrantoutupdwhen) AS warrantoutupdwhen from warrantout wo "+
			"left join customer c on wo.warrantoutcusid = customerid "+
			"left join orderm om on om.ordermid = wo.warrantoutodm "+
			"where wo.warrantoutodm is not null and wo.warrantoutcompany='"+companyid+"' and om.ordermstatue='已发货' and wo.warrantoutupdwhen >='"+startDate+"' and wo.warrantoutupdwhen <='"+endDate+"'"+query+
			"group by wo.warrantoutodm,om.ordermcode,c.customername,c.customershop,c.customerphone,c.customerid) "+
			"union all "+
			"(select wo.idwarrantout,wo.warrantoutodm,'','1',c.customershop,c.customername,c.customerphone,c.customerid,wo.warrantoutupdwhen from warrantout wo "+
			"left join customer c on wo.warrantoutcusid = customerid "+
			"where wo.warrantoutodm is null and wo.warrantoutcompany='"+companyid+"' and wo.warrantoutupdwhen >='"+startDate+"' and wo.warrantoutupdwhen <='"+endDate+"'"+query.replace("ordermcode like '%桃子妹妹%' or ", "")+" ) "+
			"order by warrantoutupdwhen desc";
		ArrayList<OutOrder> ooLi = (ArrayList<OutOrder>) selAll(OutOrder.class,selSQL);
		String[] heads = {"单据编号","种类数","客户名称","联系人","手机","出库时间"};				//表头
		String[] discard = {"idwarrantout","warrantoutodm","customerid"};			//要忽略的字段名

		FileUtil.expExcel(response, ooLi, heads, discard, "出库单");
	}
	
	//查询出库单
	public void chukudan(HttpServletRequest request, HttpServletResponse response){
		String[] queryfieldname = {
				"ordermcode",
				"customershop",
				"customername",
				"customerphone",
				"warrantoutupdwhen"
		};
		String companyid = request.getParameter("companyid");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String query = request.getParameter("query");
		if(!CommonUtil.isNull(query)){
			query = " and ("+getQuerysql(query, queryfieldname)+")";
		} else {
			query = "";
		}
		Queryinfo queryinfo = getQueryinfo(request, OutOrder.class, queryfieldname, null, new TypeToken<ArrayList<OutOrder>>() {}.getType());
		String selSQL = "(select '' idwarrantout,wo.warrantoutodm,om.ordermcode,om.ordermnum,c.customershop,c.customername,c.customerphone,c.customerid,max(wo.warrantoutupdwhen) AS warrantoutupdwhen from warrantout wo "+
			"left join customer c on wo.warrantoutcusid = customerid "+
			"left join orderm om on om.ordermid = wo.warrantoutodm "+
			"where wo.warrantoutodm is not null and wo.warrantoutcompany='"+companyid+"' and om.ordermstatue='已发货' and wo.warrantoutupdwhen >='"+startDate+"' and wo.warrantoutupdwhen <='"+endDate+"'"+query+
			"group by wo.warrantoutodm,om.ordermcode,c.customername,c.customershop,c.customerphone,c.customerid) "+
			"union all "+
			"(select wo.idwarrantout,wo.warrantoutodm,'','1',c.customershop,c.customername,c.customerphone,c.customerid,wo.warrantoutupdwhen from warrantout wo "+
			"left join customer c on wo.warrantoutcusid = customerid "+
			"where wo.warrantoutodm is null and wo.warrantoutcompany='"+companyid+"' and wo.warrantoutupdwhen >='"+startDate+"' and wo.warrantoutupdwhen <='"+endDate+"'"+query.replace("ordermcode like '%桃子妹妹%' or ", "")+" ) "+
			"order by warrantoutupdwhen desc";
		Pageinfo pageinfo = new Pageinfo(getTotal("select count(*) from ("+selSQL+") A"), selQuery(selSQL, queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	
	//修改打印次数
	public void updatePrintCount(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String ordermids = request.getParameter("ordermids");
		String ordermprinttimess = request.getParameter("ordermprinttimess");
		String[] ids = ordermids.split(",");
		String[] timess = ordermprinttimess.split(",");
		ArrayList<String> sqlLi = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			//修改 打印次数 和 修改人(操作人)
			String sql = "update orderm om set om.ordermprinttimes='"+timess[i]+"',om.updor='"+lgif.getUsername()+"' where om.ordermid='"+ids[i]+"' ";
			sqlLi.add(sql);
		}
		result = doAll(sqlLi.toArray(new String[0]));
		responsePW(response, result);
	}
	
	//新增
	public void addOrder(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		Orderm temp = cuss.get(0);
		
		String mOrdermid = CommonUtil.getNewId();
		temp.setOrdermid(mOrdermid);
		
		String odCode = DateUtils.formatDate(new Date(), "yyyyMMddhhmmss");
		String todayOd = (getTotal("orderm", "ordermtime like '"+DateUtils.getDate()+"%' and ordermcompany='"+temp.getOrdermcompany()+"'")+1)+"";
		odCode = "G"+odCode+"0000".substring(0, 4-todayOd.length())+todayOd ;
		temp.setOrdermcode(odCode);
		temp.setOrdermrightmoney(temp.getOrdermmoney());
		temp.setOrdermstatue("已下单");
		temp.setOrdermprinttimes("0");
		temp.setOrdermtime(DateUtils.getDateTime());
		ArrayList<String> sqls = new ArrayList<String> ();
		String sqlOrderm = getInsSingleSql(temp);
		sqls.add(sqlOrderm);
		//订单详情
		String orderdetjson = request.getParameter("orderdetjson");
		System.out.println("orderdetjson : " + orderdetjson);
		ArrayList<Orderd> sOrderd;
		if(CommonUtil.isNotEmpty(orderdetjson)) {
			java.lang.reflect.Type sOrderdTYPE = new com.google.gson.reflect.TypeToken<ArrayList<Orderd>>() {}.getType();
			sOrderd = CommonConst.GSON.fromJson(orderdetjson, sOrderdTYPE);
			for(Orderd mOrderd:sOrderd){
				if(mOrderd.getOrderdtype().equals("秒杀") || mOrderd.getOrderdtype().equals("买赠") || 
						mOrderd.getOrderdtype().equals("年货") || mOrderd.getOrderdtype().equals("组合") ){
					@SuppressWarnings("unchecked")
					List<Bkgoods> tgList = selAll(Bkgoods.class,"select * from bkgoods bg where bg.bkgoodsid = '"+mOrderd.getOrderdid()+"'");
					if(tgList.size() >0){
						Bkgoods editNumTG = tgList.get(0);			//修改剩余数量
						editNumTG.setBkgoodssurplus(editNumTG.getBkgoodssurplus() - mOrderd.getOrderdnum());
						result = updSingle(editNumTG,BkgoodsPoco.KEYCOLUMN);
					}
				}
				mOrderd.setOrderdid(CommonUtil.getNewId());
				mOrderd.setOrderdorderm(mOrdermid);
				mOrderd.setOrderdrightmoney(mOrderd.getOrderdmoney());
				String sqlOrderd = getInsSingleSql(mOrderd);
				sqls.add(sqlOrderd);
			}
			String[] ss = sqls.toArray(new String[0]);
			result = doAll(ss);
		}
		if(result.equals(CommonConst.FAILURE)){
			result = "{success:false,msg:'操作失败'}";
		}
		responsePW(response, result);
	}
}
