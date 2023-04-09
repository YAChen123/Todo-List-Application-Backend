package com.todoapp.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.todolist.model.UserAccountModel;
import com.todoapp.todolist.repository.UserAccountRepository;

@Service
public class UserService {
    
    @Autowired
    private UserAccountRepository userAccountRepository;

    public void addUser(UserAccountModel userAccount){
        userAccountRepository.addUser(userAccount);
    }

    public void deleteUser(int userId){
        /* TODO
        should also delete all the task created by the user
         * 
         */
        
        // Return a success message
        userAccountRepository.deleteUser(userId);
    }

    public boolean checkUserExists(UserAccountModel userAccount){
        return userAccountRepository.checkUserExists(userAccount.getEmail(), userAccount.getUsername());
    }

    public UserAccountModel findUserByEmail(String email){
        return userAccountRepository.findUserByEmail(email);
    }
    
}
