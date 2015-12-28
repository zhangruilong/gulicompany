package com.system.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.system.poco.System_powerPoco;
import com.system.pojo.System_power;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseDao;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;

/**
 * 快捷菜单 持久层
 *@author ZhangRuiLong
 */
public class System_powerDao extends BaseDao {
	/**
    * 模糊查询语句
    * @param query
    * @return "filedname like '%query%' or ..."
    */
    public String getQuerysql(String query) {
    	if(CommonUtil.isEmpty(query)) return null;
    	String querysql = "";
    	String queryfieldname[] = System_powerPoco.QUERYFIELDNAME;
    	for(int i=0;i<queryfieldname.length;i++){
    		querysql += queryfieldname[i] + " like '%" + query + "%' or ";
    	}
		return querysql.substring(0, querysql.length() - 4);
	};
	@SuppressWarnings("finally")
	public ArrayList<Treeinfo> selPowertree(String wheresql) {
		String sql = null;
		Treeinfo temp = null;
		ArrayList<Treeinfo> temps = new ArrayList<Treeinfo>();
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select * from "+System_powerPoco.TABLE+" where 1=1 ";
			if(CommonUtil.isNotEmpty(wheresql)){
				sql = sql + " and (" + wheresql + ") ";
			}
			sql += " order by " + System_powerPoco.ORDER; 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String leaf = "true";
				if(rs.getString("menulevel").endsWith("菜单")){
					String selchildwheresql = "parentid='" + rs.getString("id") + "'";
					if(selPowertree(selchildwheresql).size()!=0){
						leaf = null;
					}
				}
				temp = new Treeinfo(rs.getString("id"), rs.getString("code"), rs.getString("name"), rs.getString("detail"),
						null, null, null,leaf, rs.getString("parentid"));
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