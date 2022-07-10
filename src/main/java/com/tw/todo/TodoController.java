package com.tw.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
     ResponseEntity<List <Todo>> getAllTodos()
    {
        return new ResponseEntity<>(todoService.getAllTodo(),HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todo),HttpStatus.CREATED);
    }
}
