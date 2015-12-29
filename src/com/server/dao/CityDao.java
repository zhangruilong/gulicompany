package com.server.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.server.pojo.City;
import com.server.poco.CityPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseDao;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;

/**
 * 城市和县及街道 持久层
 *@author ZhangRuiLong
 */
public class CityDao extends BaseDao {
	/**
    * 模糊查询语句
    * @param query
    * @return "filedname like '%query%' or ..."
    */
    public String getQuerysql(String query) {
    	if(CommonUtil.isEmpty(query)) return null;
    	String querysql = "";
    	String queryfieldname[] = CityPoco.QUERYFIELDNAME;
    	for(int i=0;i<queryfieldname.length;i++){
    		querysql += queryfieldname[i] + " like '%" + query + "%' or ";
    	}
		return querysql.substring(0, querysql.length() - 4);
	};
	@SuppressWarnings("finally")
	public ArrayList<Treeinfo> selTree(String wheresql) {
		String sql = null;
		Treeinfo temp = null;
		ArrayList<Treeinfo> temps = new ArrayList<Treeinfo>();
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select * from "+CityPoco.TABLE+" where 1=1 ";
			if(CommonUtil.isNotEmpty(wheresql)){
				sql = sql + " and (" + wheresql + ") ";
			}
			sql += " order by " + CityPoco.ORDER; 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String leaf = "true";
				String selchildwheresql = "cityparent='" + rs.getString("cityid") + "'";
				if(selTree(selchildwheresql).size()!=0){
					leaf = null;
				}
				temp = new Treeinfo(rs.getString("cityid"), rs.getString("citycode"), rs.getString("cityname"), rs.getString("citydetail"),
						null, null, null,leaf, rs.getString("cityparent"));
				temps.add(temp);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally{
			connectionMan.freeConnection(CommonConst.DSNAME,conn,stmt,rs);
			return temps;
		}
	};
}