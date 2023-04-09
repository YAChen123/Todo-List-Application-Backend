package com.todoapp.todolist.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CategoryModel {
    private int id;
    private String name; 

}
