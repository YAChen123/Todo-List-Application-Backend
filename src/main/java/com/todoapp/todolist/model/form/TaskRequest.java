package com.todoapp.todolist.model.form;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private Integer id;
    private String uuid;
    private Integer userId;
    private String title;
    private String description;
    private Integer categoryId = 0;
    private LocalDate dueDate = LocalDate.now();
    private Boolean isCompleted = false;
    
}
