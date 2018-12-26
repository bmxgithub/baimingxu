package com.baimingxu.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3p0Utils {
	/*
	 * 只要我们写好配置文件xml,所有的事情都不用我们去做,C3P0会自动去做
	 */
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//封装方法,对外提供连接池
	public static DataSource getDataSource(){
		return dataSource;
	}
	//封装方法,提供链接
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	public static void closeAll(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				//该方法,将使用完的连接放回到连接池
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
