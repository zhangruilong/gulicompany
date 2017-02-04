package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.server.poco.OrdermPoco;
import com.server.poco.OrdermviewPoco;
import com.server.pojo.Company;
import com.server.pojo.Customer;
import com.server.pojo.LoginInfo;
import com.server.pojo.Orderd;
import com.server.pojo.Orderm;
import com.server.pojo.Ordermview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

/**
 * 订单相关的业务逻辑
 * @author taolichao
 */
public class CPOrderAction extends OrdermviewAction {

	//修改订单状态
	@SuppressWarnings("unchecked")
	public void updateOrdermStatue(HttpServletRequest request, HttpServletResponse response){
		String statue = request.getParameter("statue");
		String ordermid = request.getParameter("ordermid");
		//System.out.println("method:updateOrdermStatue, ordermid:"+ordermid);
		List<Orderm> omLi = (List<Orderm>) selAll(Orderm.class, 
				"select * from orderm om where om.ordermid='"+ordermid+"' and om.ordermstatue!='已删除'");
		if(omLi.size()>0){
			result = doSingle("update orderm om set om.ordermstatue='"+statue+"',om.updtime='"+DateUtils.getDateTime()+"' where om.ordermid='"+ordermid+"'", null);
		} else {
			result = "{success:true,code:400,msg:'没有找到该订单,或已被删除。'}";
		}
		responsePW(response, result);
	}
	//要打印的订单信息
	@SuppressWarnings("unchecked")
	public void priOrdermInfo(HttpServletRequest request, HttpServletResponse response){
		String ordermid = request.getParameter("ordermid");
		List<Orderm> omli = (List<Orderm>) selAll(Orderm.class, 
				"select * from orderm where ordermid='"+ordermid+"' and ordermstatue!='已删除'");
		if(omli.size()>0){
			List<Company> comLi = (List<Company>) selAll(Company.class,
					"select * from company where companyid='"+omli.get(0).getOrdermcompany()+"'");
			List<Customer> cusli = (List<Customer>) selAll(Customer.class, 
					"select * from customer where customerid='"+omli.get(0).getOrdermcustomer()+"'");
			List<Orderd> odli = selAll(Orderd.class, 
					"select * from orderd where orderdorderm='"+ordermid+"'");
			List<Object> objli = new ArrayList<>();
			objli.add(omli.get(0));
			objli.add(comLi.get(0));
			objli.add(cusli.get(0));
			objli.add(odli);
			Pageinfo pageinfo = new Pageinfo(objli);
			result = CommonConst.GSON.toJson(pageinfo);
		} else {
			result = "{success:false,code:400,msg:'要打印的订单已删除。'}";
		}
		responsePW(response, result);
	}
	
	//导出
	@SuppressWarnings("unchecked")
	public void expOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String comid = request.getParameter("comid");
		if(null==startDate){
			startDate = DateUtils.getDate()+" 00:00:00";
		}
		if(null==endDate){
			endDate = DateUtils.getDate()+" 23:59:59";
		}
		String wheresql = " ordermcompany='"+comid+"' and ordermstatue!='已删除' and ordermtime >= '"+
				startDate+"' and ordermtime <= '"+endDate+"'";
		Queryinfo queryinfo = getQueryinfo(request, Ordermview.class, OrdermviewPoco.QUERYFIELDNAME, OrdermviewPoco.ORDER, TYPE);
		queryinfo.setWheresql(queryinfo.getWheresql()==null?wheresql:queryinfo.getWheresql()+wheresql);
		queryinfo.setOrder("ordermtime desc");
		String[] heads = {"订单编号","种类数","下单金额","实际金额","支付方式","订单状态","下单时间","联系人","手机","地址","修改时间","层级","类型","客户名称"};				//表头
		String[] discard = {"ordermid","ordermcustomer","ordermcompany","ordermdetail","updor","ordermemp","openid","companyshop","companyphone","companydetail","ordermcusshop"};			//要忽略的字段名
		String name = "订单统计报表";							//文件名称
		if(!startDate.equals("") && !endDate.equals("")){
			name = startDate + "日至" + endDate + "日的" + name;
		} else if(startDate.equals("") && !endDate.equals("")){
			name = endDate + "日之前的" + name;
		} else if(endDate.equals("") && !startDate.equals("")){
			name = startDate + "日之后的" + name;
		}
		cuss = (ArrayList<Ordermview>) selAll(queryinfo);
		FileUtil.expExcel(response, cuss, heads, discard, name);
	}
	//查询订单
	public void queryOrder(HttpServletRequest request, HttpServletResponse response){
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String comid = request.getParameter("comid");
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String wheresql = " ordermcompany='"+comid+"' and ordermstatue!='已删除' and ordermtime >= '"+
				startDate+"' and ordermtime <= '"+endDate+"'";
		if(lgInfo.getPower().equals("隐藏")){
			wheresql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like '%2' or om.ordermid like '%3' or om.ordermid like '%4')";
		}
		Queryinfo queryinfo = getQueryinfo(request, Ordermview.class, OrdermviewPoco.QUERYFIELDNAME, "ordermtime desc", TYPE);
		queryinfo.setWheresql(queryinfo.getWheresql()==null?wheresql:queryinfo.getWheresql()+wheresql);
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//删除订单和订单详情
	public void delOdmAndOdd(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		ArrayList<Orderm> orLI = null;
		if(CommonUtil.isNotEmpty(json)){ orLI = CommonConst.GSON.fromJson(json, new TypeToken<ArrayList<Orderm>>() {}.getType());};
		if(orLI.size()>0){
			/*String delOdmSql = "delete from orderm om where om.ordermid='"+cuss.get(0).getOrdermid()+"'";
			String delOddSql = "delete from orderd od where od.orderdorderm='"+cuss.get(0).getOrdermid()+"'";
			result = doAll("mysql",delOdmSql,delOddSql);*/
			orLI.get(0).setOrdermstatue("已删除");
			result = updSingle(orLI.get(0),OrdermPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
}
