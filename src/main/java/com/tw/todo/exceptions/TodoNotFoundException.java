package com.tw.todo.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class TodoNotFoundException extends Exception {

    private final HttpStatus status;

    public TodoNotFoundException(Long id) {
        super("Can not find the todo of id : " + id);
        this.status = BAD_REQUEST;
    }


}
