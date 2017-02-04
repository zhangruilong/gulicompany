package com.server.action;

import java.util.ArrayList;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	public void insAll(HttpServletRequest request, HttpServletResponse response){
		LoginInfo info = (LoginInfo) request.getSession().getAttribute("loginInfo");	//登录信息
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		for(Bkgoods temp:cuss){
			int count = getTotal("select count(*) from bkgoods where bkgoodscode='"+temp.getBkgoodscode()
					+"' and bkgoodsunits='"+temp.getBkgoodsunits()+"' and bkgoodsclass='"+temp.getBkgoodsclass()+"' and bkgoodscompany='"+
					temp.getBkgoodscompany()+"'", "mysql");
			if(count==0){
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
			} else {
				result = "{success:true,code:400,msg:'编码规格重复，添加失败。'}";
			}
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
		Queryinfo queryinfo = getQueryinfo(request, Bkgoods.class, curQueryFN, BkgoodsPoco.ORDER, TYPE);
		Pageinfo pageinfo = new Pageinfo(getTotal(queryinfo), selQuery(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
