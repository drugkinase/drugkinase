package cn.edu.fudan.admis.database.base;

import java.sql.*;


//functions for connect to mysql, execute sql, and close all connection
public class BaseDao {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://10.20.2.25:3306/kiba";
	public static final String USERNAME = "zzcheng";
	public static final String PASSWORD = "123456";
	static Connection connection = null;
	static {
		try {
			connection = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Statement stmt = null;
	ResultSet resultSet = null;
	
	public Connection connectState() {
		return connection;
	}
	
	public static Connection getConnection() throws Exception{
		Class.forName(DRIVER);
		try{
			if(connection == null)
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Connection failed: " + e.getMessage());
		}
		return connection;
	}
	
	public ResultSet executeQuery(String sql) throws Exception{
		try{
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			//logger.debug("33333333");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Execution failed: " + e.getMessage());
		}
		return resultSet;
	}
	
	
	
	public void closeAll() throws Exception{
		if(null != resultSet){
			resultSet.close();
		}
		if(null != stmt){
			stmt.close();
		}
		if(null != connection) {
			connection.close();
		}
	}
}
