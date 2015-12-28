package com.system.tools;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonConst {
	//GSON
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class,new UtilDateSerializer()).setDateFormat("yyyy-MM-dd").create();
	//public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	//public static final Gson GSON = new Gson();
	//SUCCESS
	public static final String SUCCESS = "{success:true,code:202,msg:'操作成功'}";
	//FAILURE
	public static final String FAILURE = "{success:true,code:400,msg:'操作失败'}";
	//MFAILURE
	public static final String MFAILURE = "{success:false,code:400,msg:'操作失败'}";
	//NOPOWER
	public static final String NOPOWER = "{success:true,code:403,msg:'无权限操作'}";
	//SAMELOGINNAME
	public static final String SAMELOGINNAME = "{success:false,msg:'该账号已存在'}";
	//PASSWORDERRO
	public static final String PASSWORDERRO = "{success:false,code:403,msg:'账号密码错误'}";
	//NOREGEST
	public static final String NOREGEST = "{success:false,code:403,msg:'请先注册'}";
	//NOOPENID
	public static final String NOOPENID = "{success:false,code:403,msg:'微信授权失败,请重登微信'}";
	//USERSAMEERRO
	public static final String USERSAMEERRO = "{success:false,code:201,msg:'账号异常(多个账户),请联系客服人员'}";
	//USERERRO
	public static final String USERERRO = "{success:false,code:201,msg:'账号异常,请联系客服人员'}";
	//USERCHECKING
	public static final String USERCHECKING = "{success:false,code:200,msg:'账号正在审核,请耐心等待'}";
	//USERLOCK
	public static final String USERLOCK = "{success:false,code:201,msg:'账号已禁用,请联系客服人员'}";
	//INPUTEERRO
	public static final String INPUTEERRO = "{success:false,code:400,msg:'验证码错误'}";
	//DSNAME
	public static final String DSNAME = "oracle";
}
