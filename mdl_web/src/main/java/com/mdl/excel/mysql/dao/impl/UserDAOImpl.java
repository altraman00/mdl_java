package com.mdl.excel.mysql.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.mdl.excel.mysql.bean.User;
import com.mdl.excel.mysql.dao.IUserDAO;

public class UserDAOImpl extends JdbcDaoSupport implements IUserDAO {

	@Override
	public void addUser(User user) {
		String sql = "insert into user values(?,?,?)";
		this.getJdbcTemplate().update(sql, user.getId(), user.getUsername(), user.getPassword());
	}

	@Override
	public void deleteUser(int id) {
		String sql = "delete from user where id=?";
		this.getJdbcTemplate().update(sql, id);

	}

	@Override
	public void updateUser(User user) {
		String sql = "update user set username=?,password=? where id=?";
		this.getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getId());
	}

	@Override
	public String searchUserName(int id) {// 简单查询，按照ID查询，返回字符串
		String sql = "select username from user where id=?";
		// 返回类型为String(String.class)
		return this.getJdbcTemplate().queryForObject(sql, String.class, id);

	}

	@Override
	public List<User> findAll() {// 复杂查询返回List集合
		String sql = "select * from user";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());

	}

	@Override
	public User searchUser(int id) {
		String sql = "select * from user where id=?";
		return this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
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
