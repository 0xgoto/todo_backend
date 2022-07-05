package com.tw.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/todo")
    ResponseEntity<Object> index()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
