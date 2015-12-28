package com.system.tools.dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

	public static Connection getConnection() {
		String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
		//String DBURL = "jdbc:mysql://localhost:3306/test?Unicode=true&characterEncoding=gbk";
		String DBURL = "jdbc:oracle:thin:@192.168.50.9:1521:easdb";
		String DBUSER = "wzgj130408";
		String DBPASSWORD = "wzgj130408";
		Connection conn = setConnection(DBDRIVER,DBURL,DBUSER,DBPASSWORD);
		return conn;
	}
	
	public static Connection setConnection(String DBDRIVER,String DBURL,String DBUSER,String DBPASSWORD) {
		Connection conn = null;
		try {
		Class.forName(DBDRIVER).newInstance();
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
		System.out.println("Exception" + e.getMessage());
		}
		
		return conn;
		}
	
	public static void closeConnection(Connection conn,PreparedStatement pstmt,ResultSet rs) {  
		try{
			 if(rs != null){
				 rs.close();
				 rs = null;
			 } 
			}catch(SQLException e){
			 e.printStackTrace();
			}finally{
			 try{
			 if(pstmt != null){
				 pstmt.close();
				 pstmt = null;
			  }
			 }catch(SQLException e){
			 e.printStackTrace();
			 }finally{
			 try{
			 if(conn != null){
			conn.close();
			conn = null;
			 }
			}catch(SQLException e){
			 e.printStackTrace();
			}finally{
			}
			 }
			}
	}
	public static void closeConnection(Connection conn,Statement stmt,ResultSet rs) {  
		try{
			 if(rs != null){
				 rs.close();
				 rs = null;
			 } 
			}catch(SQLException e){
			 e.printStackTrace();
			}finally{
			 try{
			 if(stmt != null){
				 stmt.close();
				 stmt = null;
			  }
			 }catch(SQLException e){
			 e.printStackTrace();
			 }finally{
			 try{
			 if(conn != null){
			conn.close();
			conn = null;
			 }
			}catch(SQLException e){
			 e.printStackTrace();
			}finally{
			}
			 }
			}
	}

}
