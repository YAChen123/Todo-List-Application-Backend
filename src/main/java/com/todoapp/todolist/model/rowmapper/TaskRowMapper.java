package com.todoapp.todolist.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.todoapp.todolist.model.TaskModel;

public class TaskRowMapper implements RowMapper<TaskModel>{

    @Override
    public TaskModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskModel task = new TaskModel();
        task.setId(rs.getInt("id"));
        task.setUuid(rs.getString("uuid"));
        task.setUserId(rs.getInt("user_id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setCategoryId(rs.getInt("category_id"));
        task.setDueDate(rs.getDate("due_date").toLocalDate());
        task.setCompleted(rs.getBoolean("is_completed"));
        task.setCreatedAt(rs.getLong("created_at"));

        return task;
    }
}
