package com.todoapp.todolist.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TaskModel {
    private int id;
    private String uuid;
    private int userId;
    private String title;
    private String description;
    private int categoryId;
    private long createdAt;
    private LocalDate dueDate;
    private boolean isCompleted;
}
