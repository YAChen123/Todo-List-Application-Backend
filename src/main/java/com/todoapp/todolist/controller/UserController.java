package com.todoapp.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.todolist.controller.message.HttpResponseMessage;
import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpResponseMessage httpResponseMessage;

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId, HttpServletRequest request) {

        try {
            UserAccountModel currentUser = getCurrentUser(request);

            if(currentUser.getAuthorities().contains("ROLE_ADMIN")){
                userService.deleteUser(userId);
                return ResponseEntity.ok().body(httpResponseMessage.USER_DELETED);
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized to delete user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
