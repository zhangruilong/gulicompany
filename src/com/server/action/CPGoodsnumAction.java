package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.pojo.Goodsnum;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;

public class CPGoodsnumAction extends GoodsnumAction {

	//新增
	public void insGoodsnum(HttpServletRequest request, HttpServletResponse response){
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Goodsnum temp = cuss.get(0);
			@SuppressWarnings("unchecked")
			List<Goodsnum> reGNLi = selAll(Goodsnum.class, "select * from Goodsnum where goodsnumgoods='"+temp.getGoodsnumgoods()+
					"' and goodsnumstore='"+temp.getGoodsnumstore()+"'");
			if(reGNLi.size()==0){
				temp.setIdgoodsnum(CommonUtil.getNewId());
				result = insSingle(temp);
			} else {
				result = "{success:true,code:400,msg:'仓库和商品重复'}";
			}
		}
		responsePW(response, result);
	}
}
