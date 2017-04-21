package com.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.poco.GoodsnumPoco;
import com.server.poco.WarrantoutPoco;
import com.server.poco.WarrantoutviewPoco;
import com.server.pojo.Goods;
import com.server.pojo.Goodsnum;
import com.server.pojo.LoginInfo;
import com.server.pojo.Warrantinview;
import com.server.pojo.Warrantout;
import com.server.pojo.Warrantoutview;
import com.system.tools.CommonConst;
import com.system.tools.pojo.Pageinfo;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;
import com.system.tools.util.DateUtils;
import com.system.tools.util.FileUtil;
import com.system.tools.util.TypeUtil;

public class CPWarrantoutviewAction extends WarrantoutviewAction {

	//一键出库
	public void onekeyPlacing(HttpServletRequest request, HttpServletResponse response){
		LoginInfo lgif = (LoginInfo) request.getSession().getAttribute("loginInfo");
		String json = request.getParameter("json");
		String warrantoutwho = request.getParameter("warrantoutwho");	//默认领货人
		System.out.println("json : " + json);
		json = json.replace("\"\"", "null");
		if(!CommonUtil.isNull(json)) cuss = CommonConst.GSON.fromJson(json, TYPE);
		List<String> sqlLi = new ArrayList<String>();
		List<String> odmLi = new ArrayList<String>();
		List<Goodsnum> newGNLi = new ArrayList<Goodsnum>();		//要新增的 商品库存 集合
		List<Goodsnum> updGNLi = new ArrayList<Goodsnum>();		//修改的 "商品库存"
		String time = DateUtils.getDateTime();
		for(Warrantoutview temp:cuss){
			if(temp.getWarrantoutstatue().equals("发货请求")){
				if(odmLi.size()==0 || !odmLi.get(odmLi.size()-1).equals(temp.getWarrantoutodm())){
					odmLi.add(temp.getWarrantoutodm());
				}
				Warrantout updWar = new Warrantout();
				updWar.setIdwarrantout(temp.getIdwarrantout());	//ID
				updWar.setWarrantoutstatue("已发货");			//状态
				updWar.setWarrantoutwho(warrantoutwho);			//领货人
				updWar.setWarrantoutupdwhen(time);			//修改时间
				updWar.setWarrantoutupdwho(lgif.getUsername());	//修改人
				String updTemp = "";
				if(null != temp.getGoodsnumnum() && !temp.getGoodsnumnum().equals("undefined")){
					if(updGNLi.size()==0){
						Integer num =  Integer.parseInt(temp.getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
						Goodsnum updGN= new Goodsnum(temp.getIdgoodsnum(), null, num+"", null);
						updGNLi.add(updGN);
					} else {
						for (int i = 0; i < updGNLi.size(); i++) {
							Goodsnum gn = updGNLi.get(i);
							if(gn.getIdgoodsnum().equals(temp.getIdgoodsnum())){
								//如果要修改的 商品库存 已存在
								Integer goodsnumnum = Integer.parseInt(gn.getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
								gn.setGoodsnumnum(goodsnumnum.toString());
								break;
							} else if(i==updGNLi.size()-1){
								//如果最后一次循环时要修改的 商品库存 不存在则插入一个要修改的商品库存
								Integer num =  Integer.parseInt(temp.getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
								Goodsnum updGN= new Goodsnum(temp.getIdgoodsnum(), null, num+"", null);
								updGNLi.add(updGN);
								break;
							}
						}
					}
					updTemp = getUpdSingleSql(updWar, WarrantoutPoco.KEYCOLUMN);
					sqlLi.add(updTemp);
				} else {
					String goodsid = temp.getWarrantoutgoods();
					if(!CommonUtil.isNull(goodsid)){
						if(newGNLi.size()==0){
							//新增商品库存
							Goodsnum ngn = new Goodsnum(CommonUtil.getNewId(), goodsid, "-"+temp.getWarrantoutnum(), temp.getWarrantoutstore());
							newGNLi.add(ngn);
						} else {
							for (int i = 0; i < newGNLi.size(); i++) {
								Goodsnum gn = newGNLi.get(i);
								if(gn.getGoodsnumgoods().equals(goodsid)){
									//如果要新增的 商品库存 已存在
									Integer goodsnumnum = Integer.parseInt(gn.getGoodsnumnum()) - Integer.parseInt(temp.getWarrantoutnum());
									gn.setGoodsnumnum(goodsnumnum.toString());
									break;
								} else if(i==newGNLi.size()-1){
									//如果最后一次循环时要新增的 商品库存 不存在则插入一个要新增的商品库存
									Goodsnum newgn = new Goodsnum(CommonUtil.getNewId(), goodsid, "-"+temp.getWarrantoutnum(), temp.getWarrantoutstore());
									newGNLi.add(newgn);
									break;
								}
							}
						}
						updTemp = getUpdSingleSql(updWar, WarrantoutPoco.KEYCOLUMN);
						sqlLi.add(updTemp);
					}
				}
			}
		}
		if(newGNLi.size()>0){
			//如果有要新增的 库存总账记录 
			for (Goodsnum goodsnum : newGNLi) {
				sqlLi.add(getInsSingleSql(goodsnum));	//转化为SQL放入SQL语句的集合中
			}
		}
		if(updGNLi.size()>0){
			//如果有要修改的库存总账记录
			for (Goodsnum goodsnum : updGNLi) {
				sqlLi.add(getUpdSingleSql(goodsnum,GoodsnumPoco.KEYCOLUMN));	//转化为SQL放入SQL语句的集合中
			}
		}
		if(sqlLi.size()>0){
			String[] sqls = sqlLi.toArray(new String[0]);
			result = doAll(sqls);
			if(result.equals(CommonConst.SUCCESS)){
				List<String> odmSQLLi = new ArrayList<String>();
				for (String odmId : odmLi) {
					Integer woNum = getTotal("select count(*) from warrantout wo where wo.warrantoutstatue='发货请求' and wo.warrantoutodm='"+odmId+"' and wo.warrantoutcompany='"+lgif.getCompanyid()+"'");
					if(!CommonUtil.isNull(odmId) && woNum == 0){
						odmSQLLi.add("update orderm set ordermstatue='已发货' where ordermid='"+odmId+"'");
					}
				}
				doAll(odmSQLLi.toArray(new String[0]));
			}
		}
		responsePW(response, result);
	}
	//导出
	@SuppressWarnings("unchecked")
	public void expAllCP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Queryinfo queryinfo = getQueryinfo(request, Warrantoutview.class, WarrantoutviewPoco.QUERYFIELDNAME, WarrantoutviewPoco.ORDER, TYPE);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(!CommonUtil.isNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(!CommonUtil.isNull(startDate) && !CommonUtil.isNull(endDate)){
			sql += " and warrantoutinswhen >='"+startDate+"' and warrantoutinswhen <='"+endDate+"'";
		}
		Warrantoutview sum = (Warrantoutview) selAll(Warrantoutview.class,sql).get(0);
		if(null == sum.getWarrantoutnum()){
			sum.setWarrantoutnum("0");
		}
		sql += " order by idwarrantout desc";
		cuss = (ArrayList<Warrantoutview>) selAll(Warrantoutview.class, sql);
		String[] heads = {"商品编码","商品名称","商品规格","仓库","数量","销售单价","销售金额","状态","备注","领货人","创建时间","创建人","修改时间","修改人"};
		String[] discard = {"idwarrantout","warrantoutstore","warrantoutgoods","goodsid","goodscompany","warrantoutcompany","warrantoutgtype",
				"warrantoutggclass","warrantoutgunit","warrantoutgweight","warrantoutordnote","goodscode","goodsname","goodsunits","idgoodsnum",
				"goodsnumnum","warrantoutcusid","warrantoutcusname","warrantoutprint","warrantoutodm" };
		FileUtil.expExcel(response,cuss,heads,discard,"出库台账");
	}
	//分页查询
	@SuppressWarnings("unchecked")
	public void selQueryCP(HttpServletRequest request, HttpServletResponse response){
		Queryinfo queryinfo = getQueryinfo(request, Warrantoutview.class, WarrantoutviewPoco.QUERYFIELDNAME, WarrantoutviewPoco.ORDER, TYPE);
		String warrantoutodm = request.getParameter("warrantoutodm");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sql = "select sum(warrantoutnum) as warrantoutnum from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
		if(!CommonUtil.isNull(queryinfo.getJson())){
			String jsonsql = TypeUtil.beanToSql(queryinfo.getJson());
			if(!CommonUtil.isNull(jsonsql))
			sql += " and (" + TypeUtil.beanToSql(queryinfo.getJson()) + ") ";
		}
		if(!CommonUtil.isNull(warrantoutodm)){
			sql += " and warrantoutodm = '" + warrantoutodm+ "' ";
		}
		if(!CommonUtil.isNull(queryinfo.getWheresql())){
			sql += " and (" + queryinfo.getWheresql() + ") ";
		}
		if(!CommonUtil.isNull(queryinfo.getQuery())){
			sql += " and (" + queryinfo.getQuery() + ") ";
		}
		if(!CommonUtil.isNull(startDate) && !CommonUtil.isNull(endDate)){
			sql += " and warrantoutinswhen >='"+startDate+"' and warrantoutinswhen <='"+endDate+"'";
		}
		Warrantoutview sum = (Warrantoutview) selAll(Warrantoutview.class,sql).get(0);
		if(null == sum.getWarrantoutnum()){
			sum.setWarrantoutnum("0");
		}
		sql += " order by idwarrantout desc";
		String querySQL = sql.replace("select sum(warrantoutnum) as warrantoutnum", "select *");
		String totalSQL = sql.replace("select sum(warrantoutnum) as warrantoutnum", "select count(*)");
		cuss = (ArrayList<Warrantoutview>) selQuery(querySQL, queryinfo);
		cuss.add(sum);
		Pageinfo pageinfo = new Pageinfo(getTotal(totalSQL), cuss);
		result = CommonConst.GSON.toJson(pageinfo);
		responsePW(response, result);
	}
}
