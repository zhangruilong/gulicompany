package com.server.action.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.LoginInfo;
import com.server.pojo.entity.OrderStatisticsVO;
import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.OrderdStatistics;
import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.Ordermview;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

/**
 * 供应商后台管理系统-订单管理
 * @author tao
 *
 */
@Controller
public class Com_orderCtl {
	@Autowired
	private OrdermMapper ordermMapper;
	@Autowired
	private OrderdMapper orderdMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	//查询一段时间内有订单的客户名称
	@RequestMapping("/companySys/queryTimeCus")
	@ResponseBody
	public Map<String, Object> queryTimeCus(HttpSession session,String staTime,String endTime,String companyid,String queryShop) {
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		String today = DateUtils.getDate();
		List<String> cusNames = customerMapper.selectTimeCusNames(staTime, endTime, companyid,queryShop,info.getPower(),today);
		if(null != cusNames && cusNames.size()>0){
			map.put("msg", "success");
			map.put("cusNames", cusNames);
		} else {
			map.put("msg", "error");
		}
		return map;
	}
	//查询最新的一条订单
	@RequestMapping("/companySys/queryNewestOrderm")
	public @ResponseBody Orderm queryNewestOrderm(String ordermcompany){
		Orderm orderm = ordermMapper.selectNewestOrderm(ordermcompany);
		return orderm;
	}
	//全部订单
	@RequestMapping("/companySys/allOrder")
	public String allOrder(HttpSession session,Model model,String staTime,String endTime,Orderm order,Integer pagenow){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String today = DateUtils.getDate();
		if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		}
		if(pagenow == null){
			pagenow = 1;
		}
		System.out.println(info.getPower());
		Integer count = ordermMapper.selectByPageCount(staTime, endTime,order,info.getPower(),today);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<Ordermview> ordermList = ordermMapper.selectByPage(staTime, endTime,order,pagenow,10,info.getPower(),today);
		model.addAttribute("allOrder", ordermList);
		model.addAttribute("order", order);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/orderMana.jsp";
	}
	//删除订单
	@RequestMapping("/companySys/editOrder")
	public String editOrder(HttpSession session,Model model,Orderm order){
		order.setOrdermstatue("已删除");
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		order.setUpdtime(DateUtils.getDateTime());			//修改时间是删除时间
		order.setUpdor(info.getUsername());				//修改人
		ordermMapper.updateByPrimaryKeySelective(order);
		return "forward:allOrder.action";
	}
	//修改订单状态
	@RequestMapping("/companySys/deliveryGoods")
	public String deliveryGoods(HttpSession session,Model model,Orderm order){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		order.setUpdtime(DateUtils.getDateTime());			//修改时间
		order.setUpdor(info.getUsername());				//修改人
		ordermMapper.updateByPrimaryKeySelective(order);
		model.addAttribute("order", order);
		return "forward:allOrder.action";
	}
	//修改订单状态2
	@RequestMapping("/companySys/updateStatue")
	public @ResponseBody Integer updateStatue(HttpSession session, Orderm order){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		order.setUpdtime(DateUtils.getDateTime());			//修改时间
		order.setUpdor(info.getUsername());				//修改人
		Integer num = ordermMapper.updateByPrimaryKeySelective(order);
		return num;
	}
	//修改订单打印次数
	@RequestMapping("/companySys/updatePrintCount")
	public @ResponseBody Map<String, Object> updatePrintCount(HttpSession session, String ordermids,String ordermprinttimess){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");		//登录信息
		String[] ids = ordermids.split(",");
		String[] timess = ordermprinttimess.split(",");
		int count=0;
		for (int i = 0; i < ids.length; i++) {
			//修改 打印次数 和 修改人(操作人)
			int c = ordermMapper.updatePrintCount(ids[i], timess[i],info.getUsername());
			count += c;
		}
		if(count>0){
			resultMap.put("msg", "success");
		} else {
			resultMap.put("msg", "no");
		}
		return resultMap;
	}
/*--------------------------订单详情-----------------------------*/
	//订单详情
	@RequestMapping("/companySys/orderDetail")
	public String orderDetail(Model model,Orderm order,String staTime,String endTime,String pagenow){
		Orderm detOrder = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		Customer coddd = customerMapper.selectByPrimaryKey(detOrder.getOrdermcustomer());
		model.addAttribute("order", order);
		model.addAttribute("detOrder", detOrder);
		model.addAttribute("cusFoODInfo", coddd);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pagenow", pagenow);
		return "forward:/companySys/orderDetail.jsp";
	}
	//删除订单详情
	@RequestMapping("/companySys/deleOrderd")
	public String deleOrderd(HttpSession session, Model model,String[] orderdids,Orderm order){
		Float money = order.getOrdermmoney();
		Float rightmoney = order.getOrdermrightmoney();
		Integer ordermnum = order.getOrdermnum();
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");		//登录信息
		for (String orderdid : orderdids) {
			Orderd orderd = orderdMapper.selectByPrimaryKey(orderdid);
			money -= Float.parseFloat(orderd.getOrderdmoney());
			rightmoney -= Float.parseFloat(orderd.getOrderdrightmoney());
			ordermnum--;
			orderdMapper.deleteByPrimaryKey(orderdid);
		}
		order.setOrdermmoney(money);
		order.setOrdermrightmoney(rightmoney);
		order.setOrdermnum(ordermnum);
		order.setUpdtime(DateUtils.getDateTime());			//修改时间
		order.setUpdor(info.getUsername());				//修改人
		ordermMapper.updateByPrimaryKeySelective(order);
		return "forward:orderDetail.action";
	}
	//到'订单详情'修改页
	@RequestMapping("/companySys/doeditOrder")
	public String doeditOrder(Model model,String orderdid,Orderm order){
		Orderd orderd = orderdMapper.selectByPrimaryKey(orderdid);
		model.addAttribute("orderd", orderd);
		model.addAttribute("order", order);
		return "forward:/companySys/orderdDeta.jsp";
	}
	//修改订单详情
	@RequestMapping("/companySys/editOrderd")
	@ResponseBody
	public HashMap<String, Object> editOrderd(HttpSession session,Model model,Orderd orderd,Orderm order,Float diffOrderdmoney,Float diffOrderdrightmoney){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Orderm updateOrderm = ordermMapper.selectByPrimaryKey(order.getOrdermid());					//查询要修改的订单
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");				//登录信息
		if(null != updateOrderm && !updateOrderm.getOrdermstatue().equals("已删除")){
			Float nowOrdermmoney = updateOrderm.getOrdermmoney() - diffOrderdmoney;	//得到计算后的下单金额
			Float nowOrderdrightmoney = updateOrderm.getOrdermrightmoney() - diffOrderdrightmoney;	//计算后的实际金额
			updateOrderm.setOrdermmoney(nowOrdermmoney);				
			updateOrderm.setOrdermrightmoney(nowOrderdrightmoney);
			updateOrderm.setUpdtime(DateUtils.getDateTime());			//修改时间
			updateOrderm.setUpdor(info.getUsername());					//修改人
			ordermMapper.updateByPrimaryKeySelective(updateOrderm);										//修改下单金额和实际金额
			orderdMapper.updateByPrimaryKeySelective(orderd);
			resultMap.put("msg", "操作成功!");
		} else {
			resultMap.put("msg", "操作失败，订单不存在或已删除。");
		}
		return resultMap;
	}
	//打印订单
	@RequestMapping("/companySys/printOrder")
	public String printOrder(Model model,Orderm order){
		order = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		Company company = companyMapper.selectByPrimaryKey(order.getOrdermcompany());		//要打印的订单
		Customer customer = customerMapper.selectByPrimaryKey(order.getOrdermcustomer());
		model.addAttribute("order", order);
		model.addAttribute("printCompany", company);
		model.addAttribute("printCustomer", customer);
		return "forward:/companySys/print/print"+order.getOrdermcompany()+".jsp";
	}
	//订单商品统计
	@RequestMapping("/companySys/orderStatistics")
	public String orderStatistics(HttpSession session,Model model,String staTime,String endTime,String companyid,String queryShop,
			String quBrand,String quEmp,String quCus, String condition,Integer pagenow){
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String today = DateUtils.getDate();
		if(pagenow == null || pagenow==0){
			pagenow = 1;
		}
		if(null == staTime || staTime.equals("")){
			staTime = DateUtils.getDate()+" 00:00:00";
		}
		if(null == endTime || endTime.equals("")){
			endTime = DateUtils.getDateTime();
		}
		String[] quEmpNames = null;
		String[] quBrandNames = null;
		String[] quCusNames = null;
		if(null != quEmp && quEmp.length()>0){
			quEmpNames = quEmp.split(",");
		}
		if(null != quBrand && quBrand.length()>0){
			quBrandNames = quBrand.split(",");
		}
		if(null != quCus && quCus.length()>0){
			quCusNames = quCus.split(",");
		}
		Integer count = orderdMapper.selectByTimeCount(staTime, endTime, companyid,condition,quEmpNames,quBrandNames,
				quCusNames,info.getPower(),today);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		if(pagenow > pageCount){
			pagenow = pageCount;
		}
		List<OrderStatisticsVO> list = orderdMapper.selectByPage(staTime, endTime, companyid,condition,pagenow,10,
				quEmpNames,quBrandNames,quCusNames,info.getPower(),today);					//查询数据
		OrderdStatistics total = orderdMapper.selectOrderdStatistics(staTime, endTime, companyid,condition,quEmpNames,
				quBrandNames,quCusNames,info.getPower(),today);	//数据统计
		model.addAttribute("total", total);
		model.addAttribute("orderdList", list);
		model.addAttribute("companyid", companyid);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("quBrand", quBrand);
		model.addAttribute("quEmp", quEmp);
		model.addAttribute("quCus", quCus);
		model.addAttribute("queryShop",queryShop);
		model.addAttribute("condition", condition);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "orderStatistics.jsp";
	}
	//导出报表
	@RequestMapping("/companySys/exportReport")
	@ResponseBody
	public void exportReport(HttpSession session,HttpServletRequest request,HttpServletResponse response,String staTime,String endTime,String companyid,
			String quBrand,String quEmp,String quCus, String condition) throws Exception{
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String[] quEmpNames = null;
		String[] quBrandNames = null;
		String[] quCusNames = null;
		String quCondit = "";
		String today = DateUtils.getDate();
		if(null != quEmp && quEmp.length()>0){
			quCondit += "业务员:"+quEmp;
			quEmpNames = quEmp.split(",");
		}
		if(null != quBrand && quBrand.length()>0){
			quCondit += "品牌:"+quBrand;
			quBrandNames = quBrand.split(",");
		}
		if(null != quCus && quCus.length()>0){
			quCondit += "客户:"+quCus;
			quCusNames = quCus.split(",");
		}
		ArrayList<Orderd> list = (ArrayList<Orderd>) orderdMapper.selectByTime(staTime+":00", endTime+":00", companyid,condition,
				quEmpNames,quBrandNames,quCusNames,info.getPower());
		OrderdStatistics total = orderdMapper.selectOrderdStatistics(staTime+":00", endTime+":00", companyid,condition,quEmpNames,
				quBrandNames,quCusNames,info.getPower(),today);	//数据统计
		//String[] heads = {"商品编码","商品名称","规格","单位","单价","数量","下单金额","实际金额","重量"};				//表头
		String[] discard = {"orderdid","orderdorderm","orderddetail","orderdclass","orderdtype","orderm","orderdgoods","orderdnote"};			//要忽略的字段名
		String name = "货品销售汇总表";							//文件名称
		if(!staTime.equals("") && !endTime.equals("")){
			name = staTime + "日至" + endTime + "日的" + name;
		} else if(staTime.equals("") && !endTime.equals("")){
			name = endTime + "日之前的" + name;
		} else if(endTime.equals("") && !staTime.equals("")){
			name = staTime + "日之后的" + name;
		}
		String title = "货品销售汇总表";
		String annotation = "起始时间："+staTime+":00"+",终止时间："+endTime+":00";
		String printCondition = "数据过滤条件:"+quCondit;
		String templatePath = "companySys/templateFile/haiyanOrderdStatTemp.xls";
		orderStatisticsExpExcel(request, response, list, templatePath, name, discard, title, annotation, printCondition, total);
	}
	//导出订单报表
	@RequestMapping("/companySys/exportOrderReport")
	@ResponseBody
	public void exportOrderReport(HttpSession session,HttpServletResponse response,String staTime,String endTime,Orderm order) throws Exception{
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		String today = DateUtils.getDate();
		ArrayList<Ordermview> ordermList = (ArrayList<Ordermview>)ordermMapper.selectByCompany(staTime, endTime,order,info.getPower(),today);
		String[] heads = {"订单编号","种类数","下单金额","实际金额","支付方式","订单状态","下单时间","联系人","手机","地址","客户名称","修改时间"};				//表头
		String[] discard = {"ordermid","ordermcustomer","ordermcompany","ordermdetail","companyshop","ordermcusshop","companydetail","companyphone","ordermprinttimes","ordermcuslevel","openid","ordermcustype","updor","ordermemp","orderdList","orderdCustomer"};			//要忽略的字段名
		String name = "订单统计报表";							//文件名称
		if(!staTime.equals("") && !endTime.equals("")){
			name = staTime + "日至" + endTime + "日的" + name;
		} else if(staTime.equals("") && !endTime.equals("")){
			name = endTime + "日之前的" + name;
		} else if(endTime.equals("") && !staTime.equals("")){
			name = staTime + "日之后的" + name;
		}
		FileUtil.expExcel(response, ordermList, heads, discard, name);
	}
	//大客户下单
	@RequestMapping(value="/companySys/largeCusOrdermSave")
	@ResponseBody
	public String largeCusOrdermSave(@RequestBody Orderm orderm,HttpSession session){
		String ordermid = CommonUtil.getNewId();
		LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
		orderm.setOrdermid(ordermid);
		orderm.setOrdermtime(DateUtils.getDateTime());
		
		String date = DateUtils.getDate();
		String odCode = "G"+DateUtils.formatDate(new Date(), "yyyyMMddhhmmss");
		Orderm odm = new Orderm();
		odm.setOrdermcompany(orderm.getOrdermcompany());
		String todayOd = (ordermMapper.selectByPageCount(date+" 00:00:00", date+"23:59:59",odm,"company",null)+1)+"";	//今天的第几个订单;
		odCode += "0000".substring(0, 4-todayOd.length())+todayOd ;		//订单编码
		//System.out.println(odCode);
		orderm.setOrdermcode(odCode);			//设置订单编码
		//orderm.setOrdermemp(info.getEmpcode());		//设置订单源
		ordermMapper.insertSelective(orderm);
		for (Orderd insOD : orderm.getOrderdList()) {
			insOD.setOrderdid(CommonUtil.getNewId());
			insOD.setOrderdorderm(ordermid);
			orderdMapper.insertSelective(insOD);
		}
		return "ok";
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
				if(CommonUtil.isNotEmpty(discard)){
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
				if(CommonUtil.isNotEmpty(discard)){
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

















