package com.server.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.server.poco.WarrantcheckPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantcheck;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;

public class CPWarrantcheckAction extends WarrantcheckAction {

	//导入
	public void impWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,WarrantcheckPoco.NAME,"impAll");
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
	   String[] fieldnames = {
		 	"warrantcheckstore",
		 	"warrantcheckgoods",
		 	"warrantchecknumorg",
		 	"warrantchecknumnow",
		 	"warrantcheckstatue",
		 	"warrantcheckdetail",
		 	"warrantcheckinswho",
		 	"warrantcheckinswhen",
		 	"warrantcheckupdwho",
		 	"warrantcheckupdwhen",
	   };
		String json = FileUtil.impExcel(fileinfo.getPath(),fieldnames); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		String time = DateUtils.getDateTime();
		for(Warrantcheck temp:cuss){
			temp.setIdwarrantcheck(CommonUtil.getNewId());
			temp.setWarrantcheckinswhen(time);
			temp.setWarrantcheckinswho(lgi.getUsername());
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
	//新增
	public void insWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantcheck temp = cuss.get(0);
			String newid = CommonUtil.getNewId();
			temp.setIdwarrantcheck(newid);
			temp.setWarrantcheckinswhen(DateUtils.getDateTime());	//创建时间
			temp.setWarrantcheckinswho(lgi.getUsername());		//操作人
			String insCheckSql = getInsSingleSql(temp);
			@SuppressWarnings("unchecked")
			List<Goodsnum> gNumLi = selAll(Goodsnum.class, "select * from Goodsnum where goodsnumgoods='"+temp.getWarrantcheckgoods()+
					"' and goodsnumstore='"+temp.getWarrantcheckstore()+"'");
			if(gNumLi.size()>0){
				String updNumSql = "update goodsnum set goodsnumnum='"+temp.getWarrantchecknumnow()+"' where goodsnumgoods='"+
						temp.getWarrantcheckgoods()+"'";
				String[] sqls = {updNumSql,insCheckSql};
				result = doAll(sqls);
			} else {
				String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
						newid+"', '"+temp.getWarrantcheckgoods()+"', '"+temp.getWarrantchecknumnow()+"', '"+temp.getWarrantcheckstore()+"')";
				String[] sqls = {insNumSql,insCheckSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
	/**
	 * POI:解析Excel2003文件中的全部数据与heads封装成json
	 * @param inputfile
	 * @param heads
	 * @return 
	 */
	public static String impExcel(String inputfile, String[] heads) {
		if (inputfile.endsWith(".xls"))
			return impExcel2003(inputfile, heads);
        else if (inputfile.endsWith(".xlsx"))
        	return impExcel2007(inputfile, heads);
		return null;
	}
	public static String impExcel2003(String inputfile, String[] heads) {
		String json = "[";
		try {
			InputStream fis = new FileInputStream(inputfile);
			// 创建Excel工作薄
			HSSFWorkbook hwb = new HSSFWorkbook(fis);
			// 得到第一个工作表
			HSSFSheet sheet = hwb.getSheetAt(0);
			HSSFRow row = null;
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
				sheet = hwb.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
					row = sheet.getRow(j);
					json = json + "{";
					for (int p = 0; p < heads.length; p++) {
						String cellValue = FileUtil.getCellValue(row.getCell(p));
						if(CommonUtil.isNotEmpty(cellValue))
							json += "'" + heads[p] + "':'" + cellValue + "',";
					}
					json = json.substring(0, json.length() - 1) + "},";
				}
				json = json.substring(0, json.length() - 1) + "]";
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	public static String impExcel2007(String inputfile, String[] heads) {
		String json = "[";
		try {
			InputStream fis = new FileInputStream(inputfile);
			// 创建Excel工作薄
			XSSFWorkbook hwb = new XSSFWorkbook(fis);
			// 得到第一个工作表
			XSSFSheet sheet = hwb.getSheetAt(0);
			XSSFRow row = null;
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
				sheet = hwb.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
					row = sheet.getRow(j);
					json = json + "{";
					for (int p = 0; p < heads.length; p++) {
						String cellValue = FileUtil.getCellValue(row.getCell(p));
						if(CommonUtil.isNotEmpty(cellValue))
							json += "'" + heads[p] + "':'" + cellValue + "',";
					}
					json = json.substring(0, json.length() - 1) + "},";
				}
				json = json.substring(0, json.length() - 1) + "]";
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
