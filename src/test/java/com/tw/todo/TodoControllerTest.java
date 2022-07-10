package com.tw.todo;

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
        verify(todoService, times(1)).getAllTodo();
    }
}
