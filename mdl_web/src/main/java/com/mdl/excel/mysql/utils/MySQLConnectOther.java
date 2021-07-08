package com.mdl.excel.mysql.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MySQLConnectOther {
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	static final String DB_URL = "jdbc:mysql://localhost:3308/apmsys";
	static final String USER = "root";
	static final String PASS = "123456";

//	static final String DB_URL = "jdbc:mysql://172.29.231.177:3306/apmsys";
//	static final String USER = "root";
//	static final String PASS = "Wan0926";

	public void connectMysql(List<Object> mList) {
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

			ResultSet rs = null;

			int n = 0;
			int k = 0;
			int m = 0;

			ArrayList<String> valList;
			String sqlStr = "" ;
			for (int i = 0; i < mList.size(); i++) {
				
				m++;
				
				valList = new ArrayList<String>();
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) mList.get(i);
				for (Entry<String, String> entry : map.entrySet()) {
					valList.add(entry.getValue());
				}

				String integrationid = map.get("指标").split("\\.")[0];
				String subName = map.get("指标").split("\\.")[1];
				String metricName = map.get("指标");
				String desc = map.get("具体含义");
				String pluralUnit = map.get("单位");
				String sql = "select * from metric_dis_bak_2 where integrationid = '" + integrationid + "' and metric_name = '" + metricName + "'";
				
				rs = stmt.executeQuery(sql);

				if (rs.next()) {

					k++;
					
				} else {
					n++;
					sqlStr += ",('"+ integrationid + "', '" + subName + "', '" + metricName + "', '" + desc + "', '" + ""
							+ "', '" + "" + "', '" + "" + "', '" + pluralUnit + "', '" + "" + "')";
					
				}
			}

			try {
				String sql = "INSERT INTO `apmsys`.`metric_dis_bak_2` (`integrationid`, `subname`, `metric_name`, `description`, `short_description`, `metric_alias`, `metric_type`, `plural_unit`, `per_unit`) VALUES ";
				
				String insertSql = sql + sqlStr.substring(1, sqlStr.length());
				
				System.out.println(insertSql);
				
				String newSql = new String(insertSql.getBytes("UTF-8"), "UTF-8");
				
				boolean insertRs = stmt.execute(newSql);
				if (!insertRs) {
					System.out.println("插入成功\n");
				}
			} catch (Exception e) {
				System.out.println("主键冲突");
			}
			
			
			
			System.out.println("整个文件夹中有" + m + "条数据" + "---其中有" + k + "条数据已经在mysql中存在了" + "---新增加了" + n + "条数据");

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

	private static String analysisList(List<Object> list) {
		String sql = "";
		ArrayList<String> valList;
		for (int i = 0; i < list.size(); i++) {
			valList = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) list.get(i);
			for (Entry<String, String> entry : map.entrySet()) {
				valList.add(entry.getValue());
			}

			String integrationid = map.get("指标").split("\\.")[0];
			String subName = map.get("指标").split("\\.")[1];
			String metricName = map.get("指标");
			String desc = map.get("具体含义");
			String pluralUnit = map.get("单位");

			System.out.println(integrationid + "------" + subName + "------" + metricName + "------" + desc + "------" + pluralUnit + "==========" + valList);
		}

		return null;
	}

}
