package com.tw.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        todoService.getAllTodo();

        verify(todoRepository, times(1)).findAll();

    }

    @Test
    void shouldBeAbleToCreateATodo() {

        todo = new Todo("Test Create", false);

        todoService.createTodo(todo);

        verify(todoRepository,times(1)).save(todo);

    }
}
