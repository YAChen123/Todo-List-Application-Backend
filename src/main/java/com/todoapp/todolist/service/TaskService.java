package com.todoapp.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.todolist.model.TaskModel;
import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.model.form.TaskRequest;
import com.todoapp.todolist.model.form.TaskResponse;
import com.todoapp.todolist.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskModel addTask(UserAccountModel user, TaskRequest form){
        TaskModel task = new TaskModel();
        task.setUuid(form.getUuid());
        task.setUserId(user.getId());
        task.setTitle(form.getTitle());
        task.setDescription(form.getDescription());
        task.setDueDate(form.getDueDate());
        task.setCategoryId(form.getCategoryId());
        task.setCreatedAt(System.currentTimeMillis());
        task.setCompleted(form.getIsCompleted());

        taskRepository.addTask(task);
        return task;
    }

    public TaskModel updateTask(UserAccountModel user, TaskRequest form){

        String taskUuid = form.getUuid();

        if(taskUuid == null){
            throw new RuntimeException("Task uuid is not provided");
        }


        TaskModel task = taskRepository.getTaskByUuid(taskUuid);
        if(task == null){
            throw new RuntimeException("Task not found");
        }

        if(form.getTitle() != null){
            task.setTitle(form.getTitle());
        }
        if(form.getDescription() != null){
            task.setDescription(form.getDescription());
        }
        if(form.getCategoryId() != null){
            task.setCategoryId(form.getCategoryId());
        }
        if(form.getDueDate() != null){
            task.setDueDate(form.getDueDate());
        }
        if(form.getIsCompleted() != null){
            task.setCompleted(form.getIsCompleted());
        }
        taskRepository.updateTask(task);

        return task;
    }

    public void deleteTaskByUuid(String taskUuid){
        taskRepository.deleteTaskByUuid(taskUuid);
    }

    public void completeTaskById(TaskModel task){
        taskRepository.completeTaskById(task);
    }

    public List<TaskModel> getTasksByUserId(int userId){
        return taskRepository.getTasksByUserId(userId);
    }

    public TaskResponse getTaskResponseByAllToDoLists(List<TaskModel> todos){
        TaskResponse response = new TaskResponse();

        response.setCompleteToDos(todos.stream().filter(todo -> todo.isCompleted())
        .collect(Collectors.toList()));

        response.setIncompleteToDos(todos.stream().filter(todo -> !todo.isCompleted())
        .collect(Collectors.toList()));

        return response;
    }

}
