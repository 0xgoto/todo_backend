package com.tw.todo.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class TodoNotFoundException extends Exception {


    public TodoNotFoundException(String message) {
        super(message);

    }


}
