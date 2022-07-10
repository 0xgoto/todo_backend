package com.tw.todo.handlers;

import com.tw.todo.exceptions.TodoNotFoundException;
import com.tw.todo.handlers.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final ArrayList<String> emptyDetails = new ArrayList<>();

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdNotFoundException (TodoNotFoundException err){
        ErrorResponse errorResponse = new ErrorResponse("Invalid ID", Collections.singletonList(err.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
