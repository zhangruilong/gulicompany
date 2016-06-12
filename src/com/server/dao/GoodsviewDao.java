package com.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.server.pojo.Goodsview;
import com.server.poco.GoodsviewPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseDao;
import com.system.tools.pojo.Queryinfo;
import com.system.tools.util.CommonUtil;

/**
 * 商品 持久层
 *@author ZhangRuiLong
 */
public class GoodsviewDao extends BaseDao {
	/**
    * 模糊查询语句
    * @param query
    * @return "filedname like '%query%' or ..."
    */
    public String getQuerysql(String query) {
    	if(CommonUtil.isEmpty(query)) return null;
    	String querysql = "";
    	String queryfieldname[] = GoodsviewPoco.QUERYFIELDNAME;
    	for(int i=0;i<queryfieldname.length;i++){
    		querysql += queryfieldname[i] + " like '%" + query + "%' or ";
    	}
		return querysql.substring(0, querysql.length() - 4);
	};
	@SuppressWarnings("finally")
	public List selGoodsviews(Queryinfo queryinfo) {
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		List objs = new ArrayList();
		try {
			String sql = "select * from " + queryinfo.getType().getSimpleName() + " where 1=1 ";
			if(CommonUtil.isNotEmpty(queryinfo.getWheresql())){
				sql += " and (" + queryinfo.getWheresql() + ") ";
			}
			if(CommonUtil.isNotEmpty(queryinfo.getQuery())){
				sql += " and (" + queryinfo.getQuery() + ") ";
			}
			if(CommonUtil.isNotEmpty(queryinfo.getOrder())){
				sql += " order by " + queryinfo.getOrder();
			}
			stmt = conn.createStatement();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			//所有的属性  
	        Field[] field = queryinfo.getType().getDeclaredFields(); 
			while (rs.next()) {
				Goodsview mGoodsview = new Goodsview(rs.getString("goodsid"), rs.getString("goodscompany"), 
						rs.getString("goodscode"), rs.getString("goodsname"), rs.getString("goodsdetail"),
						rs.getString("goodsunits"), rs.getString("goodsclass"), rs.getString("goodsimage"), 
						rs.getString("goodsstatue"), rs.getString("createtime"), rs.getString("updtime"), 
						rs.getString("creator"), rs.getString("updor"), rs.getString("goodsbrand"), 
						rs.getString("goodstype"), rs.getInt("goodsorder"), rs.getString("goodsclassid"), 
						rs.getString("goodsclasscode"), rs.getString("goodsclassname"), 
						rs.getString("goodsclassparent"), rs.getString("goodsclassdetail"), 
						rs.getString("goodsclassstatue"), rs.getString("companyshop"), 
						rs.getString("companycity"), rs.getString("companyaddress"), 
						rs.getString("companydetail"), rs.getString("companystatue"), 
						rs.getString("pricesid"), rs.getString("pricesclass"), 
						rs.getInt("priceslevel"), rs.getFloat("pricesprice"), 
						rs.getString("pricesunit"), rs.getFloat("pricesprice2"), 
						rs.getString("pricesunit2"));
				objs.add(mGoodsview);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally{
			connectionMan.freeConnection(CommonConst.DSNAME,conn,stmt,rs);
	        return objs;
		}
	}
}