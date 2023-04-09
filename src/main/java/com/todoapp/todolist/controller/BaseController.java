package com.todoapp.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.todoapp.todolist.config.JwtUtils;
import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    protected UserAccountModel getCurrentUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String userEmail = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userEmail = jwtUtils.extractUserEmail(token);
        }

        if (userEmail != null) {
            UserAccountModel currentUser = userService.findUserByEmail(userEmail);
            if (currentUser != null) {
                return currentUser;
            }
        }

        throw new RuntimeException("User not authorized");
    }


}
