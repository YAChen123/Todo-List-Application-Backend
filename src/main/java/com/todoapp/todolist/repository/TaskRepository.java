package com.todoapp.todolist.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todoapp.todolist.model.TaskModel;
import com.todoapp.todolist.model.rowmapper.TaskRowMapper;

@Repository
public class TaskRepository {
    @Autowired
	private JdbcTemplate jdbcTemplate;

    public int addTask(TaskModel task){
        String sql = "INSERT INTO tasks (uuid, user_id, title, description, category_id, due_date, is_completed, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    return jdbcTemplate.update(sql, task.getUuid(), task.getUserId(), task.getTitle(), task.getDescription(), task.getCategoryId(), task.getDueDate(), task.isCompleted(), task.getCreatedAt());
    }

    public int updateTask(TaskModel task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, category_id = ?, due_date = ?, is_completed = ? where id = ? ";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getCategoryId(), task.getDueDate(), task.isCompleted(), task.getId());
    }

    public int completeTaskById(TaskModel task){
        String sql = "UPDATE tasks SET is_completed = true where id = ? ";
        return jdbcTemplate.update(sql, task.getId());
    }

    public int deleteTaskByUuid(String taskUuid){
        String sql = "DELETE from tasks WHERE uuid = ? ";
	  	return jdbcTemplate.update(sql, taskUuid);
    }

    public TaskModel getTaskByUuid(String uuid) {
        String sql = "SELECT * FROM tasks WHERE uuid = ?";
        List<TaskModel> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), uuid);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    public List<TaskModel> getTasksByUserId(int userId) {
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        List<TaskModel> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), userId);
        return tasks;
    }



}
