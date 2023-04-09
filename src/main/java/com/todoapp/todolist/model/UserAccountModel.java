package com.todoapp.todolist.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserAccountModel {
    
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String authorities = "ROLE_USER";
}
