package com.todoapp.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapp.todolist.config.JwtUtils;
import com.todoapp.todolist.controller.message.HttpResponseMessage;
import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.model.form.AuthenticationRequest;
import com.todoapp.todolist.model.form.AuthenticationResponse;
import com.todoapp.todolist.model.form.RegisterRequest;
import com.todoapp.todolist.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpResponseMessage responseMessage;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationResponse register(RegisterRequest request){
        UserAccountModel user = new UserAccountModel();
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if(request.getAuthorities() != null){
            user.setAuthorities(request.getAuthorities());
        }

        if(userAccountRepository.checkUserExists(request.getEmail(), request.getUsername())){
            throw new RuntimeException(responseMessage.USER_ALREADY_EXISTED);
        }
        
        userAccountRepository.addUser(user);

        String jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build(); 
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException(responseMessage.INVALID_USERNAME_PASSWORD);
        }
        UserAccountModel user = userAccountRepository.findUserByEmail(request.getEmail());
        String jwtToken = jwtUtils.generateToken(user);
        
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
}
