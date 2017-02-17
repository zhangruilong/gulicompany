package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	//回滚
	public void delWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantcheck temp = cuss.get(0);
			//查询商品的库存总账
			@SuppressWarnings("unchecked")
			List<Goodsnum> gnLi = selAll(Goodsnum.class,"select * from goodsnum gn where gn.goodsnumgoods='"+temp.getWarrantcheckgoods()+
					"' and goodsnumstore='"+temp.getWarrantcheckstore()+"'");
			if(gnLi.size()>0){
				String updNumSql = "update goodsnum g set g.goodsnumnum='"+temp.getWarrantchecknumorg()+
						"' where g.idgoodsnum='"+gnLi.get(0).getIdgoodsnum()+"'";
				String delTempSql = "update Warrantin set warrantinstatue='回滚' where idwarrantin='"+temp.getIdwarrantcheck()+"'";
				String[] sqls = {updNumSql,delTempSql};
				result = doAll(sqls);
			}
		}
		responsePW(response, result);
	}
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
}
