package com.tw.todo;

import com.tw.todo.exceptions.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodo(Long id) throws TodoNotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        }
        throw new TodoNotFoundException(id);
    }

    public Todo updateTodo(Todo todo, Long id) throws TodoNotFoundException {
        Todo todoToUpdate = getTodo(id);
        todo.setId(todoToUpdate.getId());
        return todoRepository.save(todo);
    }
}
