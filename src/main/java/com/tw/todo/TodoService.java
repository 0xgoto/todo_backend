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
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return optionalTodo.get();
        }
        throw new TodoNotFoundException("Not able to find id : " + id);
    }

    public Todo updateTodo(Todo todo, Long id) throws TodoNotFoundException {

        Optional<Todo> optionalTodoToUpdate = Optional.of(getTodo(id));
        if (optionalTodoToUpdate.isPresent()) {
            todo.setId(optionalTodoToUpdate.get().getId());
            return todoRepository.save(todo);
        }
        throw new TodoNotFoundException("Not able to find id : " + id);
    }

    public void deleteTodo(Long id) throws TodoNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            todoRepository.deleteById(id);
        } else {
            throw new TodoNotFoundException("Not able to find id : " + id);
        }
    }
}
