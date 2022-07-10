package com.tw.todo;

import com.tw.todo.exceptions.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) throws TodoNotFoundException {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable Long id) throws TodoNotFoundException {
        return new ResponseEntity<>(todoService.updateTodo(todo, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
