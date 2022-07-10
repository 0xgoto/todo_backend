package com.tw.todo;

import com.tw.todo.exceptions.TodoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TodoControllerTest {

    TodoService todoService;


    @BeforeEach
    void before() {
        todoService = mock(TodoService.class);
    }


    @Test
    void shouldInteractWithServiceLayerWhenGetRequestIsMade() {
        TodoController todoController = new TodoController(todoService);
        todoController.getAllTodos();
        verify(todoService, times(1)).getAllTodos();
    }

    @Test
    void shouldBeAbleToGetATodo() throws TodoNotFoundException {

        Long id = 1L;
        TodoController todoController = new TodoController(todoService);

        todoController.getTodo(id);

        verify(todoService, times(1)).getTodo(id);

    }

    @Test
    void shouldBeAbleToCreateATodo() {

        Todo todo = new Todo("Topic1", false);
        TodoController todoController = new TodoController(todoService);

        todoController.createTodo(todo);

        verify(todoService, times(1)).createTodo(todo);

    }


    @Test
    void shouldBeAbleToUpdateATodo() throws TodoNotFoundException {

        Long id = 1L;
        Todo todo = new Todo("Old Topic", false);
        TodoController todoController = new TodoController(todoService);

        todoController.updateTodo(todo, id);

        verify(todoService, times(1)).updateTodo(todo, id);

    }

    @Test
    void shouldBeAbleToDeleteATodo() {

        Long id = 1L;
        TodoController todoController = new TodoController(todoService);

        todoController.deleteTodo(id);

        verify(todoService, times(1)).deleteTodo(id);

    }
}
