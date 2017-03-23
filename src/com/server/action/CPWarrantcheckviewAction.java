package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.WarrantcheckPoco;
import com.server.poco.WarrantcheckviewPoco;
import com.server.pojo.Goodsnumview;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantcheck;
import com.server.pojo.Warrantcheckview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Fileinfo;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantcheckviewAction extends WarrantcheckviewAction {
	
	//检查导入信息
	public void impCheck(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,WarrantcheckPoco.NAME,"impAll");
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String[] fieldnames = {
				"goodscode" 			//编码
				,"goodsname" 			//名称
				,"goodsunits" 			//规格
				,"storehousename" 		//仓库
  			    ,"warrantchecknumorg" 	//应有数量
  			    ,"warrantchecknumnow" 	//现有数量
  			    ,"warrantcheckstatue" 	//状态
  			    ,"warrantcheckdetail" 	//描述
		};
		String json = FileUtil.impExcel(fileinfo.getPath(),fieldnames); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		String msg = "";
		for(int i=0; i < cuss.size(); i++){
			Warrantcheckview temp = cuss.get(i);
			//通过“经销商ID”、“商品信息”、“仓库名称”查找一条库存总账记录
			@SuppressWarnings("unchecked")
			List<Goodsnumview> gnLi = selAll(Goodsnumview.class, "select * from Goodsnumview where goodscompany='"+lgi.getCompanyid()+
					"' and goodscode='"+temp.getGoodscode()+"' and goodsunits='"+temp.getGoodsunits()+
					"' and storehousename='"+temp.getStorehousename()+"'");
			if(gnLi.size()==0){
				msg += i+",";
			}
		}
		if(msg.length()>0){
			msg = "第:"+msg+" 行信息没有查询到对应的“库存总账”信息。";
		}
		if(msg.length()>0){
			result = "{success:true,code:202,msg:'"+msg+"'}";
		}
		responsePW(response, result);
	}
	//导入
	public void impWarrantcheck(HttpServletRequest request, HttpServletResponse response){
		Fileinfo fileinfo = FileUtil.upload(request,0,null,WarrantcheckPoco.NAME,"impAll");
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String[] fieldnames = {
				"goodscode" 			//编码
				,"goodsname" 			//名称
				,"goodsunits" 			//规格
				,"storehousename" 		//仓库
  			    ,"warrantchecknumorg" 	//应有数量
  			    ,"warrantchecknumnow" 	//现有数量
  			    ,"warrantcheckstatue" 	//状态
  			    ,"warrantcheckdetail" 	//描述
		};
		String json = FileUtil.impExcel(fileinfo.getPath(),fieldnames); 
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		String time = DateUtils.getDateTime();
		String msg = "";
		String errMsg = "";
		for(int i=0; i < cuss.size(); i++){
			Warrantcheckview temp = cuss.get(i);
			//通过“经销商ID”、“商品信息”、“仓库名称”查找一条库存总账记录
			@SuppressWarnings("unchecked")
			List<Goodsnumview> gnLi = selAll(Goodsnumview.class, "select * from Goodsnumview where goodscompany='"+lgi.getCompanyid()+
					"' and goodscode='"+temp.getGoodscode()+"' and goodsunits='"+temp.getGoodsunits()+
					"' and storehousename='"+temp.getStorehousename()+"'");
			if(gnLi.size()>0){
				Goodsnumview gn = gnLi.get(0);
				//更新库存总账数量
				String udpGN = "udpate Goodsnum set goodsnumnum='"+temp.getWarrantchecknumnow()+"' where idgoodsnum='"+gn.getIdgoodsnum()+"'";
				temp.setIdwarrantcheck(CommonUtil.getNewId());
				temp.setWarrantcheckinswhen(time);
				temp.setWarrantcheckinswho(lgi.getUsername());
				String insCheck = getInsSingleSql(temp);
				String[] sqls = {udpGN,insCheck};
				result = doAll(sqls);
				if(!result.equals(CommonConst.SUCCESS)){
					errMsg += i+",";
				}
			} else {
				msg += i+",";
			}
		}
		if(msg.length()>0){
			msg = "第:"+msg+" 行信息没有查询到对应的“库存总账”信息。";
		}
		if(errMsg.length()>0){
			msg += "第:"+errMsg+" 行信息插入失败。";
		}
		if(msg.length()>0){
			result = "{success:true,code:202,msg:'"+msg+"'}";
		}
		responsePW(response, result);
	}
	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Warrantcheckview.class, WarrantcheckviewPoco.QUERYFIELDNAME, WarrantcheckviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(CommonUtil.isNotEmpty(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(CommonUtil.isNotNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(CommonUtil.isNotEmpty(startDate) && CommonUtil.isNotEmpty(endDate)){
			sql += " and warrantcheckinswhen >='"+startDate+"' and warrantcheckinswhen <='"+endDate+"'";
		}
		sql += " order by idwarrantcheck desc";
		cuss = (ArrayList<Warrantcheckview>) selAll(Warrantcheckview.class,sql);
		String[] heads = {"商品编号","商品名称","商品规格","仓库","应有数量","现有数量","盘点人","状态","描述","创建人","创建时间","修改人","修改时间"};
		String[] discard = {"idwarrantcheck","warrantcheckstore","warrantcheckgoods","goodsid","goodscompany" };
		FileUtil.expExcel(response,cuss,heads,discard,"盘点记录");
	}
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantcheckview.class, WarrantcheckviewPoco.QUERYFIELDNAME, WarrantcheckviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(CommonUtil.isNotEmpty(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(CommonUtil.isNotNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(CommonUtil.isNotEmpty(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(CommonUtil.isNotEmpty(startDate) && CommonUtil.isNotEmpty(endDate)){
			sql += " and warrantcheckinswhen >='"+startDate+"' and warrantcheckinswhen <='"+endDate+"'";
		}
		sql += " order by idwarrantcheck desc";
		String totalSQL = sql.replace("select *", "select count(*)");
		cuss = (ArrayList<Warrantcheckview>) selQuery(sql, queryinfo);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
