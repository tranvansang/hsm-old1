package org.hedspi.posgresql.hedspi_student_manager.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;

public class CoreService {
	private static CoreService instance;
	public static CoreService getInstance(){
		if (instance == null){
			instance = new CoreService();
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				Control.getInstance().getLogger().log(Level.SEVERE, "Init postgresql driver failed.\nMessage: " + e.getMessage());
				return null;
			}
		}
		return instance;
	}
	
	private Properties loginInfo;
	
	public void setLoginInfo(Properties loginInfo) {
		this.loginInfo = loginInfo;
	}

	public static boolean isGoodLogin(Properties loginInfo){
			String url = getUrl(loginInfo);
			try {
				Connection con = DriverManager.getConnection(url);
				con.close();
			} catch (SQLException e) {
				Control.getInstance().getLogger().log(Level.WARNING, "Login failed: {0}", e.getMessage());
				return false;
			}
			return true;
	}

	private static String getUrl(Properties loginInfo) {
		String url = "jdbc:postgresql://"+ loginInfo.getProperty("host", "localhost") +
				":" + loginInfo.getProperty("port", "5432") +
				"/" + loginInfo.getProperty("dbname", "hedspi") +
				"?user="+ loginInfo.getProperty("username", "Admin") +
				"&password="+loginInfo.getProperty("password", "hedspi");
//				+ "&ssl=" + loginInfo.getProperty("ssl", "false");
//		Control.getInstance().getLogger().log(Level.INFO, url);
		return url;
	}
	
	private String getUrl(){
		return getUrl(loginInfo);
	}
	
	private Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(getUrl());
			
		} catch (Throwable e){
			Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			return null;
		}
		if (conn == null){
			Control.getInstance().getLogger().log(Level.SEVERE, "Failed to connect to database");
			return null;
		}
		
		try{
			for(SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning()){
				Control.getInstance().getLogger().log(Level.WARNING, warn.getMessage());
			}
		} catch (Exception e){
			Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
		}
		return conn;
	}
	
	public ArrayList<HashMap<String,Object>> query(String queryStr){
		Control.getInstance().getLogger().log(Level.INFO, "Execute query {0}", queryStr);
		
		Connection conn = getConnection();
		if (conn == null)
			return null;
		
		Statement stmt = getStatement(conn);
		if (stmt == null){
			closeConnection(conn);
			return null;
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(queryStr);
		} catch (SQLException e) {
			Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			closeStatement(stmt);
			closeConnection(conn);
			return null;
		}
		if (rs == null){
			closeStatement(stmt);
			closeConnection(conn);
			return null;
		}
		
		ArrayList<HashMap<String, Object>> result = new ArrayList<>();
		try {
			int n = rs.getMetaData().getColumnCount();
			while (rs.next()){
				HashMap<String, Object> map = new HashMap<>();
				for(int i = 1; i <= n; i++){
					String label = rs.getMetaData().getColumnLabel(i);
					Object val = rs.getObject(i);
					map.put(label, val);
				}
				result.add(map);
			}
		} catch (SQLException e) {
			Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
		} finally{
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;

	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null)
			try{
				rs.close();
			} catch (SQLException e){
				Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			}
	}

	private void closeStatement(Statement stmt) {
		if (stmt != null){
			try{
				stmt.close();
			} catch (SQLException e){
				Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			}
		}
	}

	private Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			return null;
		}
		if (stmt == null){
			Control.getInstance().getLogger().log(Level.SEVERE, "Cannot create query");
			return null;
		}
		return stmt;
	}

	private void closeConnection(Connection conn) {
		if (conn != null)
			try{
				conn.close();
			} catch (Throwable e){
				Control.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
			}
	}
}
