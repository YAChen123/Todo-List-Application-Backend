package com.todoapp.todolist.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.todoapp.todolist.model.UserAccountModel;

public class UserAccountRowMapper implements RowMapper<UserAccountModel> {

    @Override
    public UserAccountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        UserAccountModel user = new UserAccountModel();
        user.setId(rs.getInt("id"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setAuthorities(rs.getString("authorities"));

        return user;
    }
}
