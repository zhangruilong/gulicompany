package com.server.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsclassPoco;
import com.server.pojo.Goodsclass;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;

public class CPGoodsclassAction extends GoodsclassAction {

	//得到供应商的小类
	public void queryCompanyGoodsclass(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Goodsclass.class, GoodsclassPoco.QUERYFIELDNAME, GoodsclassPoco.ORDER, TYPE);
		queryinfo.setWheresql(queryinfo.getWheresql()+" and GOODSCLASSPARENT != 'root' and goodsclassstatue != '禁用' and GOODSCLASSPARENT != 'G14630381061319233'");
		Pageinfo pageinfo = new Pageinfo(0, selAll(queryinfo));
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}














