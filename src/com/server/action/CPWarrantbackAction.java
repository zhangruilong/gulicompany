package com.server.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantbackPoco;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantback;
import com.system.tools.CommonConst;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;

public class CPWarrantbackAction extends WarrantbackAction {

	//新增
	public void addWarrantback(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgi = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(CommonUtil.isNotEmpty(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		if(cuss.size()>0){
			Warrantback temp = cuss.get(0);
			String newid = CommonUtil.getNewId();
			temp.setIdwarrantback(newid);
			temp.setWarrantbackinswhen(DateUtils.getDateTime());
			temp.setWarrantbackinswho(lgi.getUsername());
			String insBackSql = getInsSingleSql(temp);
			if(temp.getWarrantbackstatue().equals("good")){
				@SuppressWarnings("unchecked")
				List<Goodsnum> gnLi = selAll(Goodsnum.class, "select * from goodsnum where goodsnumgoods='"+
						temp.getWarrantbackgoods()+"' and goodsnumstore='"+temp.getWarrantbackstore()+"'");
				if(gnLi.size()>0){
					Goodsnum gn = gnLi.get(0);
					gn.setGoodsnumnum((Integer.parseInt(gn.getGoodsnumnum())-Integer.parseInt(temp.getWarrantbacknum()))+"");
					String updGNSql = getUpdSingleSql(gn, GoodsnumPoco.KEYCOLUMN);
					String[] sqls = {insBackSql,updGNSql};
					result = doAll(sqls);
				} else {
					String insNumSql = "INSERT INTO `abf`.`goodsnum` (`idgoodsnum`, `goodsnumgoods`, `goodsnumnum`, `goodsnumstore`) VALUES ('"+
							newid+"', '"+temp.getWarrantbackgoods()+"', '"+temp.getWarrantbacknum()+"', '"+temp.getWarrantbackstore()+"')";
					String[] sqls = {insBackSql,insNumSql};
					result = doAll(sqls);
				}
			} else {
				result = doSingle(insBackSql, null);
			}
		}
		responsePW(response, result);
	}
}
