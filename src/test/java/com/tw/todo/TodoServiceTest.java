package com.tw.todo;

import com.tw.todo.exceptions.TodoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TodoServiceTest {

    TodoService todoService;
    TodoRepository todoRepository;
    Todo todo;


    @BeforeEach
    void before() {
        todoRepository = mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void shouldInteractWithRepositoryWhenGetAllTodosFunctionIsCalled() {

        todoService.getAllTodos();

        verify(todoRepository, times(1)).findAll();

    }

    @Test
    void shouldBeAbleToGetATodo() throws TodoNotFoundException {

        Long id = 1L;
        todo = new Todo("Get a Todo",false);
        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        todoService.getTodo(id);

        verify(todoRepository,times(1)).findById(id);
    }

    @Test
    void shouldBeAbleToCreateATodo() {

        todo = new Todo("Test Create", false);

        todoService.createTodo(todo);

        verify(todoRepository,times(1)).save(todo);

    }


}
