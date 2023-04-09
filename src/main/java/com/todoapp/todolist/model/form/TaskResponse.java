package com.todoapp.todolist.model.form;


import java.util.List;

import com.todoapp.todolist.model.TaskModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private List<TaskModel> completeToDos;
    private List<TaskModel> incompleteToDos;

    
}
