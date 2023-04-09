package com.todoapp.todolist.model.form;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
    
}
