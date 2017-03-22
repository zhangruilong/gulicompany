package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.BkgoodsPoco;
import com.server.pojo.Bkgoods;
import com.server.pojo.LoginInfo;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPBkgoodsAction extends BkgoodsAction {

	//新增
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		LoginInfo info = (LoginInfo) request.getSession().getAttribute("loginInfo");	//登录信息
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Bkgoods temp = cuss.get(0);
			System.out.println("select count(*) from bkgoods where (bkgoodscode='"+temp.getBkgoodscode()+"' or bkgoodscode like 'a%"+temp.getBkgoodscode()
					+"') and bkgoodsunits='"+temp.getBkgoodsunits()+"' and bkgoodsclass='"+temp.getBkgoodsclass()+"' and bkgoodscompany='"+
					temp.getBkgoodscompany()+"'");
			int count = getTotal("select count(*) from bkgoods where (bkgoodscode='"+temp.getBkgoodscode()+"' or bkgoodscode like 'a%"+temp.getBkgoodscode()
					+"') and bkgoodsunits='"+temp.getBkgoodsunits()+"' and bkgoodsclass='"+temp.getBkgoodsclass()+"' and bkgoodscompany='"+
					temp.getBkgoodscompany()+"'", "mysql");
			if(count>0){
				String aCode = "";
				for (int i = 0; i < count; i++) {
					aCode += "a";
				}
				temp.setBkgoodscode(aCode+temp.getBkgoodscode());
			}
			if(CommonUtil.isNull(temp.getBkgoodsid())) temp.setBkgoodsid(CommonUtil.getNewId());//ID
			if(null == temp.getBkgoodsseq()) temp.setBkgoodsseq(0);			//顺序
			if(CommonUtil.isNull(temp.getBkgoodsweight())) temp.setBkgoodsweight("0");//重量
			if(null==temp.getBkgoodsallnum()) temp.setBkgoodsallnum(-1);		//全部限量
			if(null==temp.getBkgoodsnum()) temp.setBkgoodsnum(-1);		//每日限量
			if(null==temp.getBkgoodsprice()) temp.setBkgoodsprice(new Float(0));	//原价
			temp.setBkgoodssurplus(temp.getBkgoodsallnum());		//剩余数量
			temp.setBkcreatetime(DateUtils.getDateTime());		//创建时间
			temp.setBkcreator(info.getUsername());			//创建人
			result = insSingle(temp);
		}
		responsePW(response, result);
	}
	//修改
	public void updAll(HttpServletRequest request, HttpServletResponse response){
		LoginInfo info = (LoginInfo) request.getSession().getAttribute("loginInfo");	//登录信息
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Bkgoods temp:cuss){
			if(null == temp.getBkgoodsseq()) temp.setBkgoodsseq(0);			//顺序
			if(CommonUtil.isNull(temp.getBkgoodsweight())) temp.setBkgoodsweight("0");//重量
			if(null==temp.getBkgoodsallnum()) temp.setBkgoodsallnum(-1);		//全部限量
			if(null==temp.getBkgoodsnum()) temp.setBkgoodsnum(-1);		//每日限量
			if(null==temp.getBkgoodsprice()) temp.setBkgoodsprice(new Float(0));	//原价
			temp.setBkgoodsupdtime(DateUtils.getDateTime());		//修改时间
			temp.setBkgoodsupdor(info.getUsername());			//修改人
			result = updSingle(temp,BkgoodsPoco.KEYCOLUMN);
		}
		responsePW(response, result);
	}
	//分页查询供应商商品
	public void queryCompanyBKgoods(HttpServletRequest request, HttpServletResponse response){
		String[] curQueryFN = {
			 	"bkgoodscode",
			 	"bkgoodsname",
			 	"bkgoodsdetail",
			 	"bkgoodsunits",
			 	"bkgoodsunit",
			 	"bkgoodsclass",
			 	"bkgoodsstatue",
			 	"bkgoodsbrand",
			 	"bkgoodstype"
		};	//模糊查询的字段
		String type = request.getParameter("type");
		Queryinfo queryinfo = getQueryinfo(request, Bkgoods.class, curQueryFN, BkgoodsPoco.ORDER, TYPE);
		if(CommonUtil.isNotEmpty(type)){
			queryinfo.setWheresql(queryinfo.getWheresql()+" and bkgoodsscope like '%"+type+"%'");
		}
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
