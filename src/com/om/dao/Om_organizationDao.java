package com.om.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.om.pojo.Om_organization;
import com.om.poco.Om_organizationPoco;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseDao;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;

/**
 * 机构信息表 持久层
 *@author ZhangRuiLong
 */
public class Om_organizationDao extends BaseDao {
	/**
    * 模糊查询语句
    * @param query
    * @return "filedname like '%query%' or ..."
    */
    public String getQuerysql(String query) {
    	if(CommonUtil.isEmpty(query)) return null;
    	String querysql = "";
    	String queryfieldname[] = Om_organizationPoco.QUERYFIELDNAME;
    	for(int i=0;i<queryfieldname.length;i++){
    		querysql += queryfieldname[i] + " like '%" + query + "%' or ";
    	}
		return querysql.substring(0, querysql.length() - 4);
	};
	@SuppressWarnings("finally")
	public ArrayList<Treeinfo> selOmtree(String wheresql) {
		String sql = null;
		Treeinfo temp = null;
		ArrayList<Treeinfo> temps = new ArrayList<Treeinfo>();
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select * from Om_treeview where 1=1 ";
			if(CommonUtil.isNotEmpty(wheresql)){
				sql = sql + " and (" + wheresql + ") ";
			}
			//sql += ORDER; 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String iconCls = null;
				if(rs.getString("detail").equals("女")){
					iconCls = "../../../sysimages/user_red.png";
				}else if(rs.getString("detail").equals("男")){
					iconCls = "../../../sysimages/user_gray.png";
				}else if(rs.getString("detail").equals("position")){
					iconCls = "../../../sysimages/position.gif";
				}else if(rs.getString("detail").equals("org")){
					iconCls = "../../../sysimages/org.gif";
				}
				temp = new Treeinfo(rs.getString("id"), rs.getString("code"), rs.getString("name"), rs.getString("detail"),
						iconCls, null, null,rs.getString("leaf"), rs.getString("parentid"));
				temps.add(temp);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally{
			connectionMan.freeConnection(CommonConst.DSNAME,conn,stmt,rs);
			return temps;
		}
	}	
}