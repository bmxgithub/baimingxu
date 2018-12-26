package com.baimingxu.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		//从ThreadLocal容器中获取连接
		Connection conn = tl.get();
		if (conn == null) {
			//如果没获取到,从c3p0连接池中获取连接
			conn = C3p0Utils.getConnection();
			//将从连接池中获取的连接,放入到容器中
			tl.set(conn);
		}
		return conn;
	}

	//开启事务
	public static void begin() throws SQLException {
		getConnection().setAutoCommit(false);
	}

	//提交事务
	public static void commit() throws SQLException {
		getConnection().commit();
	}

	//回滚事务
	public static void rollback() throws SQLException {
		getConnection().rollback();
	}

	//释放资源
	public static void close() throws SQLException {
		getConnection().close();
	}
}
