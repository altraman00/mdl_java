package com.mdl.excel.mysql.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mdl.excel.mysql.bean.User;
import com.mdl.excel.mysql.dao.IUserDAO;

public class MysqlJDBCTest {

	// public static void main(String[] args) {
	//
	// ApplicationContext ac = new
	// ClassPathXmlApplicationContext("jdbc-servlet.xml");
	// IUserDAO dao=(IUserDAO) ac.getBean("userdao");
	// User user = dao.searchUser(1);
	//
	// System.out.println(user.getUsername() + "---" + user.getEmail());
	//
	// }

	@Test // 增
	public void demo1() {
		User user = new User();
		user.setId(3);
		user.setUsername("admin");
		user.setPassword("123456");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		dao.addUser(user);

	}

	@Test // 改
	public void demo2() {
		User user = new User();
		user.setId(1);
		user.setUsername("admin");
		user.setPassword("admin");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		dao.updateUser(user);
	}

	@Test // 删
	public void demo3() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		dao.deleteUser(3);
	}

	@Test // 查（简单查询，返回字符串）
	public void demo4() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		String name = dao.searchUserName(1);
		System.out.println(name);
	}

	@Test // 查（简单查询，返回对象）
	public void demo5() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		User user = dao.searchUser(1);
		System.out.println(user.getUsername());
	}

	@Test // 查（复杂查询，返回对象集合）
	public void demo6() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-servlet.xml");
		IUserDAO dao = (IUserDAO) applicationContext.getBean("userDao");
		List<User> users = dao.findAll();
		System.out.println(users.size());
	}

}
