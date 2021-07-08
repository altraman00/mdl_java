package com.mdl.excel.mysql.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectMain {
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// 数据库的用户名与密码，需要根据自己的设置
//	static final String DB_URL = "jdbc:mysql://localhost:3308/apmsys";
//	static final String USER = "root";
//	static final String PASS = "123456";
	
	static final String DB_URL = "jdbc:mysql://172.29.231.177:3306/apmsys";
	static final String USER = "root";
	static final String PASS = "Wan0926";
	

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 打开链接
			System.out.println("连接数据库...\n");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println(" 实例化Statement对...\n");
			stmt = conn.createStatement();
			String sql;
			sql = "select * from metric_dis limit 1";
			ResultSet rs = stmt.executeQuery(sql);

			String insertSql = null;

			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				String integrationid = rs.getString("integrationid") == null ? "" : rs.getString("integrationid");
				String subname = rs.getString("subname") == null ? "" : rs.getString("subname");
				String metric_name = rs.getString("metric_name") == null ? "" : rs.getString("metric_name");
				String description = rs.getString("description") == null ? "" : rs.getString("description");
				String short_description = rs.getString("short_description") == null ? ""
						: rs.getString("short_description");
				String metric_alias = rs.getString("metric_alias") == null ? "" : rs.getString("metric_alias");
				String metric_type = rs.getString("metric_type") == null ? "" : rs.getString("metric_type");
				String plural_unit = rs.getString("plural_unit") == null ? "" : rs.getString("plural_unit");
				String per_unit = rs.getString("per_unit") == null ? "" : rs.getString("per_unit");
				Date createtime = rs.getDate("createtime");
				Date updatetime = rs.getDate("updatetime");

				insertSql = "INSERT INTO `apmsys`.`metric_dis_bak` (`integrationid`, `subname`, `metric_name`, `description`, `short_description`, `metric_alias`, `metric_type`, `plural_unit`, `per_unit`, `createtime`, `updatetime`) VALUES ('"
						+ integrationid + "', '" + subname + "', '" + metric_name + "', '" + description + "', '"
						+ short_description + "', '" + metric_alias + "', '" + metric_type + "', '" + plural_unit
						+ "', '" + per_unit + "', '" + createtime + "', '" + updatetime + "');";

				System.out.println(insertSql + "\n");

				// // 输出数据
				// System.out.print("integrationid: " + integrationid + "\n");
				// System.out.print("subname: " + subname + "\n");
				// System.out.print("metric_name: " + metric_name + "\n");
				// System.out.print("description: " + description + "\n");
				// System.out.print("short_description: " + short_description +
				// "\n");
				// System.out.print("metric_alias: " + metric_alias + "\n");
				// System.out.print("metric_type: " + metric_type + "\n");
				// System.out.print("plural_unit: " + plural_unit + "\n");
				// System.out.print("per_unit: " + per_unit + "\n");
				// System.out.print("createtime: " + createtime + "\n");
				// System.out.print("updatetime: " + updatetime + "\n");
			}

//			try {
//				boolean insertRs = stmt.execute(insertSql);
//				if (!insertRs) {
//					System.out.println("插入成功\n");
//				}
//			} catch (Exception e) {
//				System.out.println("主键冲突");
//			}

			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}
