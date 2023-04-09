package com.todoapp.todolist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.model.rowmapper.UserAccountRowMapper;

@Repository
public class UserAccountRepository {

    @Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int addUser(UserAccountModel userAccount){
		String sql = "INSERT INTO users (firstname, lastname, email, username, password, authorities) VALUES (?, ?, ?, ?, ?, ?)";
	    return jdbcTemplate.update(sql, userAccount.getFirstname(), userAccount.getLastname(), userAccount.getEmail(), userAccount.getUsername(), userAccount.getPassword(),
		userAccount.getAuthorities());
    }

	// public int updateUser(UserAccountModel userAccount){
	// 	String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, username = ?, password = ? where id = ? ";
	//   	return jdbcTemplate.update(sql, userAccount.getFirstname(), userAccount.getLastname(), userAccount.getEmail(), userAccount.getUsername(), userAccount.getPassword(), userAccount.getId());
	// }

	public int deleteUser(int userId){
		String sql = "DELETE from users WHERE id = ? ";
	  	return jdbcTemplate.update(sql, userId);
	}

	public boolean checkUserExists(String email, String username){
		// Query the database to check if a user with the specified email or username exists
		String sql = "SELECT COUNT(*) FROM users WHERE email = ? OR username = ?";
    	int count = jdbcTemplate.queryForObject(sql, Integer.class, email, username);

		// Return true if a duplicate user was found, false otherwise
		return count > 0;
	}

	public UserAccountModel findUserByEmail(String email){
		String sql = "SELECT * FROM users WHERE email = ?";
		return jdbcTemplate.queryForObject(sql, new UserAccountRowMapper(), email);
	}
}
