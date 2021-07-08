package com.mdl.excel.mysql.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.mdl.excel.mysql.bean.User;

@Component
public class TestConnMysqlDataSource{

	@Autowired
	private DriverManagerDataSource dataSource;
	
	public static void main(String[] args) {
//		//或者ClassPathXmlApplicationContext
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:excel-servlet.xml");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:jdbc-servlet.xml");
		
		TestConnMysqlDataSource icm = ac.getBean(TestConnMysqlDataSource.class);
		
		User user = icm.Test();
		
		System.out.println(user.getUsername() + "---" + user.getEmail());
	}
	
	
	private User Test(){
		
		String sql="select * from user where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(sql, new UserRowMapper(), 1);
		
	}
	
	class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			return user;
		}
		
	}
	
}
