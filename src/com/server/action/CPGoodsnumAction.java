package com.server.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
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

import com.google.gson.reflect.TypeToken;
import com.server.poco.GoodsnumPoco;
import com.server.pojo.CustomerStatisticVO;
import com.server.pojo.Goods;
import com.server.pojo.Goodsnum;
import com.server.pojo.InventoryVO;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPGoodsnumAction extends GoodsnumAction {

	//商品进销存变动表
	@SuppressWarnings("unchecked")
	public void goodsnumTable(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String startDate = request.getParameter("startDate");	//起始时间
		String endDate = request.getParameter("endDate");		//终止时间
		//仓库
		String storehouseSQL = CommonUtil.isEmpty(request.getParameter("storehouseid"))?"":" and gn.goodsnumstore='"+request.getParameter("storehouseid")+"'";
		String sql = "select g.goodsname,g.goodsunits,sh.storehousename, "+
					"(select sum(warrantinnum) from warrantin where warrantingoods=g.goodsid and warrantinstore=sh.storehouseid and warrantinstatue!='已回滚' and warrantininswhen>='"+startDate+"' and warrantininswhen<='"+endDate+"' group by warrantingoods) as innum, "+
					"(select sum(warrantoutnum) from warrantout where warrantoutgoods=g.goodsid and warrantoutstore=sh.storehouseid and warrantoutstatue='已发货' and warrantoutinswhen>='"+startDate+"' and warrantoutinswhen<='"+endDate+"' group by warrantoutgoods) as outnum, "+
					"(select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='完好退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods) as outback, "+
					"(select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='采购退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods) as inback, "+
					"sum(gn.goodsnumnum) goodsnumnum, "+
					"(ifnull(sum(gn.goodsnumnum),0)-ifnull((select sum(warrantinnum) from warrantin where warrantingoods=g.goodsid and warrantinstore=sh.storehouseid and warrantinstatue!='已回滚' and warrantininswhen>='"+startDate+"' and warrantininswhen<='"+endDate+"' group by warrantingoods),0)+ifnull((select sum(warrantoutnum) from warrantout where warrantoutgoods=g.goodsid and warrantoutstore=sh.storehouseid and warrantoutstatue='已发货' and warrantoutinswhen>='"+startDate+"' and warrantoutinswhen<='"+endDate+"' group by warrantoutgoods),0)-ifnull((select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='完好退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods),0)+ifnull((select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='采购退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods),0)) as quondamnum "+
					"from goodsnum gn "+
					"left outer join goods g on gn.goodsnumgoods=g.goodsid "+
					"left outer join storehouse sh on gn.goodsnumstore=sh.storehouseid "+
					"where g.goodscompany='"+lInfo.getCompanyid()+"' "+storehouseSQL+
					"group by g.goodsid,g.goodsname,g.goodsunits,sh.storehouseid,sh.storehousename "+
					"order by outnum desc,outback desc";
		Queryinfo queryinfo = getQueryinfo(request, InventoryVO.class, null, null, new TypeToken<ArrayList<InventoryVO>>() {}.getType());
		String totalSQL = "select count(*) from ("+sql+") as A";
		//查询总计信息
		String sumSQL = "select sum(quondamnum) quondamnum,sum(innum) innum,sum(outnum) outnum,sum(outback) outback,sum(inback) inback,sum(goodsnumnum) goodsnumnum from ("+sql+") as A";
		InventoryVO sum = (InventoryVO) selAll(InventoryVO.class, sumSQL).get(0);
		//查询列表信息
		List<InventoryVO> invLi = selQuery(sql, queryinfo);
		invLi.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), invLi);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
	//新增
	@SuppressWarnings("unchecked")
	public void insGoods(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String goodsnum = request.getParameter("goodsnum");
		String goodsnumstore = request.getParameter("goodsnumstore");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		List<Goods> gooLi = new ArrayList<Goods>();
		if(CommonUtil.isNotEmpty(json)) gooLi = CommonConst.GSON.fromJson(json,  new TypeToken<ArrayList<Goods>>() {}.getType());
		if(gooLi.size()>0 && CommonUtil.isNotEmpty(goodsnum) && CommonUtil.isNotEmpty(goodsnumstore)){
			Goods addGoods = gooLi.get(0);
			//查询是否有重复的商品
			List<Goods> isRe = selAll(Goods.class, "select * from goods where goodscode='"+addGoods.getGoodscode()+
					"' and goodsunits='"+addGoods.getGoodsunits()+"' and goodscompany='"+lInfo.getCompanyid()+"'");
			if(isRe.size()==0){
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
				gn.setGoodsnumnum(goodsnum);
				gn.setGoodsnumstore(goodsnumstore);
				gn.setGoodsnumgoods(newid);				//商品ID
				String addGNSql = getInsSingleSql(gn);			//新增库存总账的sql
				String[] sqls = {addGooSql,addGNSql};
				result = doAll(sqls);
			} else {
				Goods goods = isRe.get(0);
				//检查是否有重复的库存总账记录
				List<Goodsnum> reGnLi = selAll(Goodsnum.class, "select * from Goodsnum where goodsnumgoods='"+goods.getGoodsid()+
						"' and goodsnumstore='"+goodsnumstore+"'");
				if(reGnLi.size()==0){
					//新增库存总账记录
					Goodsnum gn = new Goodsnum();
					gn.setIdgoodsnum(CommonUtil.getNewId());
					gn.setGoodsnumnum(goodsnum);
					gn.setGoodsnumstore(goodsnumstore);
					gn.setGoodsnumgoods(goods.getGoodsid());				//商品ID
					result = insSingle(gn);
				} else {
					result = "{success:true,code:400,msg:'当前新增的库存总账记录已存在'}";
				}
			}
		}
		responsePW(response, result);
	}
	
	//导出进销存变动表信息
	@SuppressWarnings("unchecked")
	public void expJinXiaoCun(HttpServletRequest request, HttpServletResponse response) throws Exception{
		LoginInfo lInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String startDate = request.getParameter("startDate");	//起始时间
		String endDate = request.getParameter("endDate");		//终止时间
		//仓库
		String storehouseSQL = CommonUtil.isEmpty(request.getParameter("storehouseid"))?"":" and gn.goodsnumstore='"+request.getParameter("storehouseid")+"'";
		//仓库名称
		String storehousename = request.getParameter("storehousename");
		String sql = "select g.goodsname,g.goodsunits,sh.storehousename, "+
				"(select sum(warrantinnum) from warrantin where warrantingoods=g.goodsid and warrantinstore=sh.storehouseid and warrantinstatue!='已回滚' and warrantininswhen>='"+startDate+"' and warrantininswhen<='"+endDate+"' group by warrantingoods) as innum, "+
				"(select sum(warrantoutnum) from warrantout where warrantoutgoods=g.goodsid and warrantoutstore=sh.storehouseid and warrantoutstatue='已发货' and warrantoutinswhen>='"+startDate+"' and warrantoutinswhen<='"+endDate+"' group by warrantoutgoods) as outnum, "+
				"(select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='完好退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods) as outback, "+
				"(select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='采购退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods) as inback, "+
				"sum(gn.goodsnumnum) goodsnumnum, "+
				"(ifnull(sum(gn.goodsnumnum),0)-ifnull((select sum(warrantinnum) from warrantin where warrantingoods=g.goodsid and warrantinstore=sh.storehouseid and warrantinstatue!='已回滚' and warrantininswhen>='"+startDate+"' and warrantininswhen<='"+endDate+"' group by warrantingoods),0)+ifnull((select sum(warrantoutnum) from warrantout where warrantoutgoods=g.goodsid and warrantoutstore=sh.storehouseid and warrantoutstatue='已发货' and warrantoutinswhen>='"+startDate+"' and warrantoutinswhen<='"+endDate+"' group by warrantoutgoods),0)-ifnull((select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='完好退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods),0)+ifnull((select sum(warrantbacknum) from warrantback where warrantbackgoods=g.goodsid and warrantbackstore=sh.storehouseid and warrantbackstatue='采购退货' and warrantbackinswhen>='"+startDate+"' and warrantbackinswhen<='"+endDate+"' group by warrantbackgoods),0)) as quondamnum "+
				"from goodsnum gn "+
				"left outer join goods g on gn.goodsnumgoods=g.goodsid "+
				"left outer join storehouse sh on gn.goodsnumstore=sh.storehouseid "+
				"where g.goodscompany='"+lInfo.getCompanyid()+"' "+storehouseSQL+
				"group by g.goodsid,g.goodsname,g.goodsunits,sh.storehouseid,sh.storehousename "+
				"order by outnum desc,outback desc";
		//查询总计信息
		String sumSQL = "select sum(quondamnum) quondamnum,sum(innum) innum,sum(outnum) outnum,sum(outback) outback,sum(inback) inback,sum(goodsnumnum) goodsnumnum from ("+sql+") as A";
		InventoryVO totalVo = (InventoryVO) selAll(InventoryVO.class, sumSQL).get(0);
		Queryinfo queryinfo = getQueryinfo(request, InventoryVO.class, null, null, new TypeToken<ArrayList<InventoryVO>>() {}.getType());
		ArrayList<InventoryVO> invLi = (ArrayList<InventoryVO>) selQuery(sql, queryinfo);
		String name = "商品进销存变动表";			//文件名称
		if(!startDate.equals("") && !endDate.equals("")){
			name = startDate + "日至" + endDate + "日的" + name;
		} else if(startDate.equals("") && !endDate.equals("")){
			name = endDate + "日之前的" + name;
		} else if(endDate.equals("") && !startDate.equals("")){
			name = startDate + "日之后的" + name;
		}
		String templatePath = "templateFile/jinXiaoCunTemp.xls";		//模板文件路径
		String annotation = "起始时间："+startDate+":00"+"，终止时间："+endDate+":00";
		String quCondit = "数据过滤条件: 仓库:"+storehousename;
		String[] discard = new String[0];			//要忽略的字段名
		expJinXiaoCunExcel(request, response, invLi, templatePath, name, discard, annotation, quCondit, totalVo);
	}
	
	/**
	 * 订单商品统计的导出Excel(根据Excel模板文件)
	 * @param response
	 * @param temps		需要导出的数据集合
	 * @param heads		表头
	 * @param discard	要忽略的字段名
	 * @param name		文件名称
	 * @param annotation	注释
	 * @param total	最后一行的总计
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void expJinXiaoCunExcel(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,String expName,
			String[] discard, String annotation, String printCondition, InventoryVO total) throws Exception{
		if(templatePath.endsWith(".xls")){
			cusStatisticsExpExcel2003(request, response, temps, templatePath,discard, expName,
					annotation, printCondition, total);
		}else if(templatePath.endsWith(".xlsx")){
			cusStatisticsExcel2007(request, response, temps, templatePath,discard, expName,
					annotation, printCondition, total);
		}
	}
	public static void cusStatisticsExpExcel2003(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard, String expName, String annotation, String printCondition, InventoryVO total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		HSSFWorkbook hwb = new HSSFWorkbook(fis);
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFRow row;
		HSSFCell cell;
		
		//时间
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		HSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 6;// 写入各条记录，每条记录对应Excel中的一行
		int  innumIndex = 0;
		int  outnumIndex = 0;
		int  outbackIndex = 0;
		int  inbackIndex = 0;
		int  goodsnumnumIndex = 0;
		int  quondamnumIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(CommonUtil.isNotEmpty(discard) && discard.length>0){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("innum")){		//订单数量
							innumIndex = iRow;
						}
						if(field.getName().equals("outnum")){	//总金额
							outnumIndex = iRow;
						}
						if(field.getName().equals("outback")){		//订单数量
							outbackIndex = iRow;
						}
						if(field.getName().equals("inback")){	//总金额
							inbackIndex = iRow;
						}
						if(field.getName().equals("goodsnumnum")){		//订单数量
							goodsnumnumIndex = iRow;
						}
						if(field.getName().equals("quondamnum")){	//总金额
							quondamnumIndex = iRow;
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
				for (int j = 0; j < fields.length-discard.length; j++) {
					String cellText = "";
					if(j==0){
						cellText = "合计";
					} else if(j == innumIndex){
						cellText = total.getInnum().toString();
					} else if(j == outnumIndex){
						cellText = total.getOutnum().toString();
					} else if(j == outbackIndex){
						cellText = total.getOutback().toString();
					} else if(j == inbackIndex){
						cellText = total.getInback().toString();
					} else if(j == goodsnumnumIndex){
						cellText = total.getGoodsnumnum().toString();
					} else if(j == quondamnumIndex){
						cellText = total.getQuondamnum().toString();
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
	public static void cusStatisticsExcel2007(HttpServletRequest request, HttpServletResponse response,ArrayList<?> temps,String templatePath,
			String[] discard,String expName, String annotation, String printCondition, InventoryVO total) throws Exception{
		String filePath = request.getServletContext().getRealPath("/")
				+ templatePath;
		InputStream fis = new FileInputStream(filePath);
		XSSFWorkbook hwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = hwb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		//时间
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(annotation);
		//筛选条件
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(printCondition);
		
		XSSFCellStyle tableStyle = hwb.createCellStyle();  		//表样式
		tableStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//边框
		tableStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		tableStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int iLine = 6;// 写入各条记录，每条记录对应Excel中的一行
		int  innumIndex = 0;
		int  outnumIndex = 0;
		int  outbackIndex = 0;
		int  inbackIndex = 0;
		int  goodsnumnumIndex = 0;
		int  quondamnumIndex = 0;
		for (int k = 0; k < temps.size(); k++) {
			Object obj = temps.get(k);
			Field[] fields = obj.getClass().getDeclaredFields();
			row = sheet.createRow(iLine);
			int iRow = 0;// 写入每条记录对应Excel中的一列
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);// 忽略访问权限，私有的也可以访问
				boolean discardflag = true;
				if(CommonUtil.isNotEmpty(discard) && discard.length>0){
					for (int p = 0; p < discard.length; p++) {
						if (field.getName().equals(discard[p])) {
							discardflag = false;
							break;
						}
					}
				}
				if (discardflag) {
					if(k==0){
						if(field.getName().equals("innum")){		//订单数量
							innumIndex = iRow;
						}
						if(field.getName().equals("outnum")){	//总金额
							outnumIndex = iRow;
						}
						if(field.getName().equals("outback")){		//订单数量
							outbackIndex = iRow;
						}
						if(field.getName().equals("inback")){	//总金额
							inbackIndex = iRow;
						}
						if(field.getName().equals("goodsnumnum")){		//订单数量
							goodsnumnumIndex = iRow;
						}
						if(field.getName().equals("quondamnum")){	//总金额
							quondamnumIndex = iRow;
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
					} else if(j == innumIndex){
						cellText = total.getInnum().toString();
					} else if(j == outnumIndex){
						cellText = total.getOutnum().toString();
					} else if(j == outbackIndex){
						cellText = total.getOutback().toString();
					} else if(j == inbackIndex){
						cellText = total.getInback().toString();
					} else if(j == goodsnumnumIndex){
						cellText = total.getGoodsnumnum().toString();
					} else if(j == quondamnumIndex){
						cellText = total.getQuondamnum().toString();
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
}











