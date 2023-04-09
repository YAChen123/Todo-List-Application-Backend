package com.todoapp.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.todolist.controller.message.HttpResponseMessage;
import com.todoapp.todolist.model.TaskModel;
import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.model.form.TaskRequest;
import com.todoapp.todolist.model.form.TaskResponse;
import com.todoapp.todolist.service.TaskService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tasks")
public class TaskController extends BaseController{
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private HttpResponseMessage httpResponseMessage;

   @PostMapping("/task/add")
   public ResponseEntity<?> addTask(@RequestBody TaskRequest form, HttpServletRequest request){
        try {
            UserAccountModel user = getCurrentUser(request);
            TaskModel task = taskService.addTask(user, form);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }

   @PutMapping("/task/complete")
   public ResponseEntity<?> completeTask(@RequestBody TaskRequest form, HttpServletRequest request){
        try {

            UserAccountModel user = getCurrentUser(request);
            TaskModel task = taskService.updateTask(user, form);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }

   @PutMapping("/task/incomplete")
   public ResponseEntity<?> incompleteTask(@RequestBody TaskRequest form, HttpServletRequest request){
        try {
            UserAccountModel user = getCurrentUser(request);
            TaskModel task = taskService.updateTask(user, form);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }



   @PutMapping("/task/edit")
   public ResponseEntity<?> editTask(@RequestBody TaskRequest form, HttpServletRequest request){
        try {

            UserAccountModel user = getCurrentUser(request);
            TaskModel task = taskService.updateTask(user, form);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }


   @DeleteMapping("/task/{taskUuid}/delete")
   public ResponseEntity<?> deleteTask(@PathVariable("taskUuid") String taskUuid){
        try {
            taskService.deleteTaskByUuid(taskUuid);
            return ResponseEntity.ok().body("task deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }

   @GetMapping("/user/get")
   public ResponseEntity<?> getTasksByUser(HttpServletRequest request){
        try {
            UserAccountModel user = getCurrentUser(request);
            List<TaskModel> tasks = taskService.getTasksByUserId(user.getId());
            TaskResponse response = taskService.getTaskResponseByAllToDoLists(tasks);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
   }


}
