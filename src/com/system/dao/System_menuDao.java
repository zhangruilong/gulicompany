package com.system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.system.pojo.System_powerview;
import com.system.tools.CommonConst;
import com.system.tools.base.BaseDao;
import com.system.tools.pojo.Treeinfo;
import com.system.tools.util.CommonUtil;

public class System_menuDao extends BaseDao {
	@SuppressWarnings("finally")
	public ArrayList<System_powerview> selMenuRemoveQuick(String query,String userid) {
		String sql = null;
		System_powerview temp = null;
		ArrayList<System_powerview> temps = new ArrayList<System_powerview>();
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select * from system_powerview t where menulevel like '%菜单' ";
			if(CommonUtil.isNotEmpty(query)){
				sql =sql+" and (code like '"+query+"' or name like '"+query+"' or parentname like '"+query+"'";
			}		
			sql += " and t.id not in (select powerid from system_power_quick where userid = '" + userid + "') order by code";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				temp = new System_powerview(rs.getString("id")
 				,rs.getString("code")
 				,rs.getString("name")
 				,rs.getString("detail")
 				,rs.getString("parentid")
 				,rs.getString("menulevel")
 				,rs.getString("entrance")
 				,rs.getString("menuorder")
 				,rs.getString("parentname")
 				,rs.getString("iconcls")
 				,rs.getString("hreftarget")
				);
				temps.add(temp);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally{
			connectionMan.freeConnection(CommonConst.DSNAME,conn,stmt,rs);
			return temps;
		}
	}
	@SuppressWarnings("finally")
	public ArrayList<Treeinfo> selMenu(String menulevel,String parentid,String userid) {
		String sql = null;
		Treeinfo temp = null;
		ArrayList<Treeinfo> temps = new ArrayList<Treeinfo>();
		Connection  conn=connectionMan.getConnection(CommonConst.DSNAME); 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select id,code,name,detail,entrance,menulevel,parentname,iconcls,hreftarget from System_MenuView where 1=1 ";
			if(CommonUtil.isNotEmpty(menulevel)){
				sql =sql+" and menulevel like '"+menulevel+"' ";
			}
			if(CommonUtil.isNotEmpty(parentid)){
				sql =sql+" and parentid = '"+parentid+"' ";
			}
			if(CommonUtil.isNotEmpty(userid)){
				sql =sql+" and userid = '"+userid+"' ";
			}
			sql += " order by menuorder "; 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String leaf = null;
				if(CommonUtil.isNotEmpty(rs.getString("menulevel"))&&(rs.getString("menulevel")).equals("叶子菜单")){
					leaf="true";
				}
				temp = new Treeinfo(rs.getString("id"), rs.getString("code"), rs.getString("name"), rs.getString("detail"),
						rs.getString("iconcls"), rs.getString("entrance"), rs.getString("hreftarget"),leaf, rs.getString("parentname"));
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