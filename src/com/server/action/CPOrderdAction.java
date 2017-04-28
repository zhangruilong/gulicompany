package com.server.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.server.pojo.CustomerStatisticVO;
import com.server.pojo.LoginInfo;
import com.server.pojo.OrderStatisticsVO;
import com.server.pojo.Orderd;
import com.server.pojo.OrderdStatistics;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

public class CPOrderdAction extends OrderdAction{
	//得到一段时间内订单的品牌
	public void selectTimeOrderdGoodsBrand(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String querySql = "select od.orderdbrand from orderd od left join orderm om on om.ordermid = od.orderdorderm "+
					"where om.ordermcompany = '"+lgInfo.getCompanyid()+"' and om.ordermtime >= '"+startDate+"' and om.ordermtime <= '"+endDate+"' "+
					"and om.ordermstatue != '已删除' group by od.orderdbrand";
		Pageinfo pageinfo = new Pageinfo(0, selAll(Orderd.class, querySql));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//客户订单统计
	@SuppressWarnings("unchecked")
	public void customerStatistics(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		String selectsql = "select  distinct c.customerid as customerid,c.customershop as customershop,c.customername as customername, "+
			"c.customerphone as customerphone,c.customercity as customercity,c.customerxian as customerxian, "+
			"c.customeraddress as customeraddress,c.createtime as createtime,cc.createtime as cccreatetime, "+
			"cc.ccustomerid as ccustomerid,count(om.ordermid) as odm_num, sum(om.ordermrightmoney) as odm_money from customer c "+
			"left outer join ccustomer cc on c.customerid = cc.ccustomercustomer "+
			"left outer join orderm om on om.ordermcustomer = c.customerid where cc.ccustomercompany = '"+lgInfo.getCompanyid()+"' "+
			"and om.ordermstatue != '已删除' and om.ordermcompany = '"+lgInfo.getCompanyid()+"' and om.ordermtime >= '"+startDate+
			"' and om.ordermtime <= '"+endDate+"' ";
		if(lgInfo.getPower().equals("隐藏")){
			selectsql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like "
					+ "'%2' or om.ordermid like '%3' or om.ordermid like '%4') ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			selectsql += "and ( "+
				"cc.createtime like '%"+queryinfo.getQuery()+"%' or "+
				"c.customershop like '%"+queryinfo.getQuery()+"%' ) ";
		}
		String countsql = "SELECT count(1) AS rowcount FROM ("+selectsql+
				"group by c.customerid,c.customershop,c.customername,c.customerphone,c.customercity,c.customerxian,c.customeraddress,c.createtime,cc.createtime,cc.ccustomerid  ) A";
		String totalSql = selectsql.replace("distinct c.customerid as customerid,c.customershop as customershop,c.customername as customername, "+
			"c.customerphone as customerphone,c.customercity as customercity,c.customerxian as customerxian, "+
			"c.customeraddress as customeraddress,c.createtime as createtime,cc.createtime as cccreatetime, "+
			"cc.ccustomerid as ccustomerid,count(om.ordermid) as odm_num, sum(om.ordermrightmoney) as odm_money"
			, "count(om.ordermid) as odm_num,sum(om.ordermrightmoney) as odm_money");
		CustomerStatisticVO totalVo = (CustomerStatisticVO) selAll(CustomerStatisticVO.class, totalSql).get(0);
		selectsql += "group by c.customerid,c.customershop,c.customername,c.customerphone,c.customercity,c.customerxian,c.customeraddress,c.createtime,cc.createtime,cc.ccustomerid "+
				"order by isnull(cccreatetime) , cccreatetime,customerid desc ";
		queryinfo.setType(CustomerStatisticVO.class);
		List<CustomerStatisticVO> info = selQuery(selectsql, queryinfo);
		info.add(totalVo);
		Pageinfo pageinfo = new Pageinfo(getTotal(countsql), info);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	
	//订单商品统计
	@SuppressWarnings("unchecked")
	public void orderdStatistics(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request);
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cusNames = request.getParameter("cusNames");		//客户名称
		String empNames = request.getParameter("empNames");		//业务员名称
		String brandNames = request.getParameter("brandNames");		//业务员名称
		
		String selectsql = "select "+
				"od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight, "+
				"sum(od.ORDERDMONEY) sumorderdmoney, "+
				"sum(od.ORDERDRIGHTMONEY) sumorderdrightmoney, "+
				"sum(od.orderdnum) sumorderdnum "+
			"from orderd od left join orderm om on od.orderdorderm = om.ordermid ";
		if(!CommonUtil.isNull(empNames))
			selectsql += "left join ccustomer cc on cc.ccustomercustomer = om.ordermcustomer ";
		selectsql += "where om.ordermcompany = '"+lgInfo.getCompanyid()+"' ";
		selectsql += "and om.ordermstatue != '已删除' and ordermtime >= '"+startDate+"' and ordermtime <= '"+endDate+"' ";
		if(lgInfo.getPower().equals("隐藏")){
			selectsql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like '%2' or om.ordermid like '%3' or om.ordermid like '%4')";
		}
		//查询条件:cusNames
		if(!CommonUtil.isNull(cusNames)){
			selectsql += "and (";
			for (String cusName : cusNames.split(",")) {
				selectsql += "om.ordermcusshop='"+cusName+"' or ";
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		//查询条件:empNames
		if(!CommonUtil.isNull(empNames)){
			selectsql += "and (";
			for (String empName : empNames.split(",")) {
				if(!empName.equals("无填充")){
					selectsql += "cc.createtime='"+empName+"' or ";
				} else {
					selectsql += "cc.createtime is null or ";
				}
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		//查询条件:brandNames
		if(!CommonUtil.isNull(brandNames)){
			selectsql += "and (";
			for (String brandName : brandNames.split(",")) {
				if(!brandName.equals("无填充")){
					selectsql += "od.orderdbrand='"+brandName+"' or ";
				} else {
					selectsql += "od.orderdbrand is null or od.orderdbrand='' or ";
				}
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			selectsql += "and (od.orderdcode like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdname like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdunits like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdunit like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdnum like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdprice like '%"+queryinfo.getQuery()+"%' ) ";
		}
		String countsql = "SELECT count(1) AS rowcount FROM ("+selectsql+"group by od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight ) A";
		String sumsql = selectsql.replace("od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight, ", 
				"sum(od.orderdweight) as orderdweight,");
		OrderStatisticsVO sumResult = (OrderStatisticsVO) selAll(OrderStatisticsVO.class, sumsql, "mysql").get(0);
		selectsql += "group by od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight "
				+ "order by od.orderdname,od.orderdunits,od.orderdcode,od.orderdprice,od.orderdweight ";
		queryinfo.setType(OrderStatisticsVO.class);
		Integer total = getTotal(countsql);
		List<OrderStatisticsVO> voLi = selQuery(selectsql, queryinfo);
		sumResult.setOrderdprice("合计");
		voLi.add(sumResult);
		Pageinfo pageinfo = new Pageinfo(total, voLi);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	
	//修改订单详情(订单商品)
	@SuppressWarnings("unchecked")
	public void orderdEdit(HttpServletRequest request, HttpServletResponse response){
		String orderdjson = request.getParameter("orderdjson");
		System.out.println("orderdjson : " + orderdjson);
		Float ordermmoney = new Float(0.0);				
		Float ordermrightmoney = new Float(0.0);
		Integer ordermnum = 0;
		String udpateOrdermSql = null;
		/* 修改订单商品 */
		if(!CommonUtil.isNull(orderdjson)){
			cuss = CommonConst.GSON.fromJson(orderdjson, TYPE);
		}
		Orderd odd = cuss.get(0);
		String updateOrderdSql = "update orderd od set od.orderdnum='"+odd.getOrderdnum()+
				"',od.orderdmoney='"+odd.getOrderdmoney()+
				"',od.orderdrightmoney='"+odd.getOrderdrightmoney()+
				"' where od.orderdid='"+odd.getOrderdid()+"'";
		/* 修改订单 */
		List<Orderd> odOdds = selAll(Orderd.class, "select * from orderd od where od.orderdorderm='"+odd.getOrderdorderm()+"'");
		if(odOdds.size() > 0){
			ordermnum = odOdds.size();
			for (Orderd odItem : odOdds) {
				if(odItem.getOrderdid().equals(odd.getOrderdid())){
					ordermmoney += odd.getOrderdmoney();
					ordermrightmoney += odd.getOrderdrightmoney();
				} else {
					ordermmoney += odItem.getOrderdmoney();
					ordermrightmoney += odItem.getOrderdrightmoney();
				}
			}
			udpateOrdermSql = "update orderm om set om.ordermnum='"+ordermnum+
					"',om.ordermmoney='"+ordermmoney+
					"',om.ordermrightmoney='"+ordermrightmoney+
					"',om.updtime='"+DateUtils.getDateTime()+
					"' where om.ordermid='"+odd.getOrderdorderm()+"'";
			ArrayList<String> sqls = new ArrayList<String>();
			sqls.add(updateOrderdSql);
			sqls.add(udpateOrdermSql);
			result = doAll(sqls);
		}
		responsePW(response, result);
	}
	
	//导出客户订单统计
	@SuppressWarnings("unchecked")
	public void expCusOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		String selectsql = "select  distinct c.customerid as customerid,c.customershop as customershop,c.customername as customername, "+
			"c.customerphone as customerphone,c.customercity as customercity,c.customerxian as customerxian, "+
			"c.customeraddress as customeraddress,c.createtime as createtime,cc.createtime as cccreatetime, "+
			"cc.ccustomerid as ccustomerid,count(om.ordermid) as odm_num, sum(om.ordermrightmoney) as odm_money from customer c "+
			"left outer join ccustomer cc on c.customerid = cc.ccustomercustomer "+
			"left outer join orderm om on om.ordermcustomer = c.customerid where cc.ccustomercompany = '"+lgInfo.getCompanyid()+"' "+
			"and om.ordermstatue != '已删除' and om.ordermcompany = '"+lgInfo.getCompanyid()+"' and om.ordermtime >= '"+startDate+
			"' and om.ordermtime <= '"+endDate+"' ";
		if(lgInfo.getPower().equals("隐藏")){
			selectsql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like "
					+ "'%2' or om.ordermid like '%3' or om.ordermid like '%4') ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			selectsql += "and ( "+
				"cc.createtime like '%"+queryinfo.getQuery()+"%' or "+
				"c.customershop like '%"+queryinfo.getQuery()+"%' ) ";
		}
		//查询合计信息的sql语句
		String totalSql = selectsql.replace("distinct c.customerid as customerid,c.customershop as customershop,c.customername as customername, "+
				"c.customerphone as customerphone,c.customercity as customercity,c.customerxian as customerxian, "+
				"c.customeraddress as customeraddress,c.createtime as createtime,cc.createtime as cccreatetime, "+
				"cc.ccustomerid as ccustomerid,count(om.ordermid) as odm_num, sum(om.ordermrightmoney) as odm_money"
				, "count(om.ordermid) as odm_num,sum(om.ordermrightmoney) as odm_money");
		//合计信息
		CustomerStatisticVO totalVo = (CustomerStatisticVO) selAll(CustomerStatisticVO.class, totalSql).get(0);
		selectsql += "group by c.customerid,c.customershop,c.customername,c.customerphone,c.customercity,c.customerxian,c.customeraddress,c.createtime,cc.createtime,cc.ccustomerid "+
				"order by isnull(cccreatetime) , cccreatetime,customerid desc ";
		String[] discard = {"customerid","customercode","customerpsw","customercity","customerxian",
					"customertype","customerlevel","openid","customerdetail",
					"customerstatue","createtime","updtime","collectList",};			//要忽略的字段名
		String name = "谷粒网商品销售汇总表";			//文件名称
		if(!startDate.equals("") && !endDate.equals("")){
			name = startDate + "日至" + endDate + "日的" + name;
		} else if(startDate.equals("") && !endDate.equals("")){
			name = endDate + "日之前的" + name;
		} else if(endDate.equals("") && !startDate.equals("")){
			name = startDate + "日之后的" + name;
		}
		//查询信息列表
		ArrayList<CustomerStatisticVO> list = (ArrayList<CustomerStatisticVO>)  selAll(CustomerStatisticVO.class,selectsql);
		String templatePath = "templateFile/cusOrderStatistics.xls";		//模板文件路径
		String title = "谷粒网商品销售汇总表";
		String annotation = "起始时间："+startDate+":00"+",终止时间："+endDate+":00";
		String quCondit = "数据过滤条件: "+queryinfo.getQuery();
		cusStatisticsExpExcel(request, response, list, templatePath, name, discard, title, annotation, quCondit, totalVo);
	}
	
	//导出订单商品统计
	@SuppressWarnings("unchecked")
	public void exprderdSta(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request);
		LoginInfo lgInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");		//登录信息
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cusNames = request.getParameter("cusNames");		//客户名称
		String empNames = request.getParameter("empNames");		//业务员名称
		String brandNames = request.getParameter("brandNames");		//业务员名称
		
		String selectsql = "select "+
				"od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight, "+
				"sum(od.ORDERDMONEY) sumorderdmoney, "+
				"sum(od.ORDERDRIGHTMONEY) sumorderdrightmoney, "+
				"sum(od.orderdnum) sumorderdnum "+
			"from orderd od right join orderm om on od.orderdorderm = om.ordermid "+
		  	"left join customer c on c.customerid = om.ordermcustomer "+
		  	"left join ccustomer cc on cc.ccustomercustomer = om.ordermcustomer "+
			"where om.ordermcompany = '"+lgInfo.getCompanyid()+"' "+
			"and cc.ccustomercompany = '"+lgInfo.getCompanyid()+"' "+
			"and om.ordermstatue != '已删除' and ordermtime >= '"+startDate+"' and ordermtime <= '"+endDate+"' ";
		if(lgInfo.getPower().equals("隐藏")){
			selectsql += "and (om.ordermtime like '"+DateUtils.getDate()+"%' or om.ordermid like '%0' or om.ordermid like '%1' or om.ordermid like '%2' or om.ordermid like '%3' or om.ordermid like '%4')";
		}
		//查询条件:cusNames
		if(!CommonUtil.isNull(cusNames)){
			selectsql += "and (";
			for (String cusName : cusNames.split(",")) {
				selectsql += "c.customershop='"+cusName+"' or ";
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		//查询条件:empNames
		if(!CommonUtil.isNull(empNames)){
			selectsql += "and (";
			for (String empName : empNames.split(",")) {
				if(!empName.equals("无填充")){
					selectsql += "cc.createtime='"+empName+"' or ";
				} else {
					selectsql += "cc.createtime is null or ";
				}
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		//查询条件:brandNames
		if(!CommonUtil.isNull(brandNames)){
			selectsql += "and (";
			for (String brandName : brandNames.split(",")) {
				if(!brandName.equals("无填充")){
					selectsql += "od.orderdbrand='"+brandName+"' or ";
				} else {
					selectsql += "od.orderdbrand is null or ";
				}
			}
			selectsql = selectsql.substring(0, selectsql.length()-3) + ")";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			selectsql += "and (od.orderdcode like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdname like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdunits like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdunit like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdnum like '%"+queryinfo.getQuery()+"%' or "+
							"od.orderdprice like '%"+queryinfo.getQuery()+"%' ) ";
		}
		String oldStr = "od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight, "+
				"sum(od.ORDERDMONEY) sumorderdmoney, "+
				"sum(od.ORDERDRIGHTMONEY) sumorderdrightmoney, "+
				"sum(od.orderdnum) sumorderdnum";
		String newStr = "sum(od.orderdnum) as numtotal, sum(od.orderdmoney) as moneytotal, "+
				"sum(od.orderdrightmoney) as rightmoneytotal, sum(od.orderdweight) as weighttotal";
		String sumSql = selectsql.replace(oldStr, newStr);		//统计数据的sql语句
		//查询数据的sql语句
		selectsql += "group by od.orderdcode,od.orderdname,od.orderdunits,od.orderdunit,od.orderdprice,od.orderdweight "
				+ "order by od.orderdname,od.orderdunits,od.orderdcode,od.orderdprice,od.orderdweight ";
		
		String quCondit = "数据过滤条件: ";
		if(null != empNames && empNames.length()>0){
			quCondit += " 业务员:"+empNames+";";
		}
		if(null != brandNames && brandNames.length()>0){
			quCondit += " 品牌:"+brandNames+";";
		}
		if(null != cusNames && cusNames.length()>0){
			quCondit += " 客户:"+cusNames+";";
		}
		
		ArrayList<OrderStatisticsVO> statLi = (ArrayList<OrderStatisticsVO>) selAll(OrderStatisticsVO.class, selectsql);
		OrderdStatistics sumInfo = ((ArrayList<OrderdStatistics>) selAll(OrderdStatistics.class, sumSql)).get(0);
		String[] discard = {};			//要忽略的字段名
		String name = "货品销售汇总表";							//文件名称
		if(!startDate.equals("") && !endDate.equals("")){
			name = startDate + "日至" + endDate + "日的" + name;
		} else if(startDate.equals("") && !endDate.equals("")){
			name = endDate + "日之前的" + name;
		} else if(endDate.equals("") && !startDate.equals("")){
			name = startDate + "日之后的" + name;
		}
		String title = "货品销售汇总表";
		String annotation = "起始时间："+startDate+":00"+",终止时间："+endDate+":00";
		String templatePath = "templateFile/haiyanOrderdStatTemp.xls";
		
		orderStatisticsExpExcel(request, response, statLi, templatePath, name, discard, title, annotation, quCondit, sumInfo);;
	}
	
	/**
	 * 订单商品统计的导出Excel(根据Excel模板文件)
	 * @param response
	 * @param temps		需要导出的数据集合
	 * @param heads		表头
	 * @param discard	要忽略的字段名
	 * @param name		文件名称
	 * @param title		标题
	 * @param annotation	注释
	 * @param total	最后一行的总计
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void orderStatisticsExpExcel(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,String expName,
			String[] discard,String title, String annotation, String printCondition, OrderdStatistics total) throws Exception{
		if(templatePath.endsWith(".xls")){
			orderStatisticsExpExcel2003(request, response, temps, templatePath,discard, expName,title, 
					annotation, printCondition, total);
		}else if(templatePath.endsWith(".xlsx")){
			orderStatisticsExcel2007(request, response, temps, templatePath,discard, expName,title, 
					annotation, printCondition, total);
		}
	}
	public static void orderStatisticsExpExcel2003(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard, String expName,String title, String annotation, String printCondition, OrderdStatistics total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		HSSFWorkbook hwb = new HSSFWorkbook(fis);
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFRow row;
		HSSFCell cell;
		
		//时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		HSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 4;// 写入各条记录，每条记录对应Excel中的一行
		int  numtotalIndex = 0;
		int  moneytotalIndex = 0;
		int  rightmoneytotalIndex = 0;
		int  weighttotalIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(!CommonUtil.isNull(discard)){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("sumorderdnum")){
							numtotalIndex = iRow;
						}
						if(field.getName().equals("sumorderdmoney")){
							moneytotalIndex = iRow;
						}
						if(field.getName().equals("sumorderdrightmoney")){
							rightmoneytotalIndex = iRow;
						}
						if(field.getName().equals("orderdweight")){
							weighttotalIndex = iRow;
						}
					}
					cell = row.createCell(iRow);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(field.get(obj)));			//
					iRow++;
				}
			}
			iLine++;
			if(k==temps.size()-1){
				//最后一行的总计
				row = sheet.createRow(iLine);
				for (int j = 0; j < fields.length-discard.length; j++) {
					String cellText = "";
					if(j==0){
						cellText = "合计";
					} else if(j == numtotalIndex){
						cellText = total.getNumtotal();
					} else if(j == moneytotalIndex){
						cellText = total.getMoneytotal();
					} else if(j == rightmoneytotalIndex){
						cellText = total.getRightmoneytotal();
					} else if(j == weighttotalIndex){
						cellText = total.getWeighttotal();
					}
					cell = row.createCell(j);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(cellText);
				}
			}
		}
		
		//打印日期
		row = sheet.createRow(iLine+1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("打印日期：");
		cell = row.createCell(1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(DateUtils.getDate());
		
		//注释
		row = sheet.createRow(iLine+2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("注：本期存=上期存+本期入-本期出+销售退货-进货退货");
		//注释第二行
		row = sheet.createRow(iLine+3);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("上期存：即起始时间前的存货数量 ；本期存：即截止终止时间时的存货数量");
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ new String((expName + ".xls").getBytes("GBK"), "ISO8859_1")
				+ "\"");
		response.setContentType("application/download");
		OutputStream out = response.getOutputStream();
		hwb.write(out);
		out.flush();
		out.close();
	}
	public static void orderStatisticsExcel2007(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard,String expName,String title, String annotation, String printCondition, OrderdStatistics total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		XSSFWorkbook hwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = hwb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		//时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		XSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 4;// 写入各条记录，每条记录对应Excel中的一行
		int  numtotalIndex = 0;
		int  moneytotalIndex = 0;
		int  rightmoneytotalIndex = 0;
		int  weighttotalIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(!CommonUtil.isNull(discard)){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("orderdnum")){
							numtotalIndex = iRow;
						}
						if(field.getName().equals("orderdmoney")){
							moneytotalIndex = iRow;
						}
						if(field.getName().equals("orderdrightmoney")){
							rightmoneytotalIndex = iRow;
						}
						if(field.getName().equals("orderdweight")){
							weighttotalIndex = iRow;
						}
					}
					cell = row.createCell(iRow);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(field.get(obj)));			//
					iRow++;
				}
			}
			iLine++;
			if(k==temps.size()-1){
				//最后一行的总计
				row = sheet.createRow(iLine);
				for (int j = 0; j < fields.length-discard.length; j++) {
					String cellText = "";
					if(j==0){
						cellText = "合计";
					} else if(j == numtotalIndex){
						cellText = total.getNumtotal();
					} else if(j == moneytotalIndex){
						cellText = total.getMoneytotal();
					} else if(j == rightmoneytotalIndex){
						cellText = total.getRightmoneytotal();
					} else if(j == weighttotalIndex){
						cellText = total.getWeighttotal();
					}
					cell = row.createCell(j);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(cellText);
				}
			}
		}
		
		//打印日期
		row = sheet.createRow(iLine+1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("打印日期：");
		cell = row.createCell(1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(DateUtils.getDate());
		
		//注释
		row = sheet.createRow(iLine+2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("注：本期存=上期存+本期入-本期出+销售退货-进货退货");
		//注释第二行
		row = sheet.createRow(iLine+3);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("上期存：即起始时间前的存货数量 ；本期存：即截止终止时间时的存货数量");
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ new String((expName + ".xlsx").getBytes("GBK"), "ISO8859_1")
				+ "\"");
		response.setContentType("application/download");
		OutputStream out = response.getOutputStream();
		hwb.write(out);
		out.flush();
		out.close();
	}
	
	/**
	 * 订单商品统计的导出Excel(根据Excel模板文件)
	 * @param response
	 * @param temps		需要导出的数据集合
	 * @param heads		表头
	 * @param discard	要忽略的字段名
	 * @param name		文件名称
	 * @param title		标题
	 * @param annotation	注释
	 * @param total	最后一行的总计
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void cusStatisticsExpExcel(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,String expName,
			String[] discard,String title, String annotation, String printCondition, CustomerStatisticVO total) throws Exception{
		if(templatePath.endsWith(".xls")){
			cusStatisticsExpExcel2003(request, response, temps, templatePath,discard, expName,title, 
					annotation, printCondition, total);
		}else if(templatePath.endsWith(".xlsx")){
			cusStatisticsExcel2007(request, response, temps, templatePath,discard, expName,title, 
					annotation, printCondition, total);
		}
	}
	public static void cusStatisticsExpExcel2003(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard, String expName,String title, String annotation, String printCondition, CustomerStatisticVO total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		HSSFWorkbook hwb = new HSSFWorkbook(fis);
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFRow row;
		HSSFCell cell;
		
		//时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		HSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 4;// 写入各条记录，每条记录对应Excel中的一行
		int  numtotalIndex = 0;
		int  moneytotalIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(!CommonUtil.isNull(discard)){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("odm_num")){		//订单数量
							numtotalIndex = iRow;
						}
						if(field.getName().equals("odm_money")){	//总金额
							moneytotalIndex = iRow;
						}
					}
					cell = row.createCell(iRow);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(field.get(obj)));
					iRow++;
				}
			}
			iLine++;
			if(k==temps.size()-1){
				//最后一行的总计
				row = sheet.createRow(iLine);
				for (int j = 0; j <= fields.length-discard.length; j++) {
					String cellText = "";
					if(j==0){
						cellText = "合计";
					} else if(j == numtotalIndex){
						cellText = total.getOdm_num().toString();
					} else if(j == moneytotalIndex){
						cellText = total.getOdm_money().toString();
					}
					cell = row.createCell(j);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(cellText);
				}
			}
		}
		
		//打印日期
		row = sheet.createRow(iLine+1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("打印日期：");
		cell = row.createCell(1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(DateUtils.getDate());
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ new String((expName + ".xls").getBytes("GBK"), "ISO8859_1")
				+ "\"");
		response.setContentType("application/download");
		OutputStream out = response.getOutputStream();
		hwb.write(out);
		out.flush();
		out.close();
	}
	public static void cusStatisticsExcel2007(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard,String expName,String title, String annotation, String printCondition, CustomerStatisticVO total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		XSSFWorkbook hwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = hwb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		//时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		XSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 4;// 写入各条记录，每条记录对应Excel中的一行
		int  numtotalIndex = 0;
		int  moneytotalIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(!CommonUtil.isNull(discard)){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("odm_num")){		//订单数量
							numtotalIndex = iRow;
						}
						if(field.getName().equals("odm_money")){	//总金额
							moneytotalIndex = iRow;
						}
					}
					cell = row.createCell(iRow);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(field.get(obj)));			//
					iRow++;
				}
			}
			iLine++;
			if(k==temps.size()-1){
				//最后一行的总计
				row = sheet.createRow(iLine);
				for (int j = 0; j <= fields.length-discard.length; j++) {
					String cellText = "";
					if(j==0){
						cellText = "合计";
					} else if(j == numtotalIndex){
						cellText = total.getOdm_num().toString();
					} else if(j == moneytotalIndex){
						cellText = total.getOdm_money().toString();
					}
					cell = row.createCell(j);
					cell.setCellStyle(tableStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(cellText);
				}
			}
		}
		
		//打印日期
		row = sheet.createRow(iLine+1);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("打印日期：");
		cell = row.createCell(1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(DateUtils.getDate());
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ new String((expName + ".xlsx").getBytes("GBK"), "ISO8859_1")
				+ "\"");
		response.setContentType("application/download");
		OutputStream out = response.getOutputStream();
		hwb.write(out);
		out.flush();
		out.close();
	}
}








