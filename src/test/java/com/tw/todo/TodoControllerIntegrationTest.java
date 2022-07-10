package com.tw.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TodoControllerIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void before() {
        todoRepository.deleteAll();
    }


    @Test
    void shouldBeAbleToGetAllTodos() throws Exception {

        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Title 1", false));
        todos.add(new Todo("Title 2", false));
        todoRepository.saveAll(todos);

        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        toJson(todos)
                ));

    }

    @Test
    void shouldBeAbleToGetATodo() throws Exception {

        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Title 1", false));
        todos.add(new Todo("Title 2", false));
        todoRepository.saveAll(todos);

        mockMvc.perform(get("/todo/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        toJson(todos.get(1))
                ));
    }

    @Test
    void shouldBeAbleToCreateATodo() throws Exception {

        Todo todo = new Todo("Title 1", false);

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(todo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(todo.getTitle()))
                .andExpect(jsonPath("$.completed").value(todo.isCompleted()));

    }




    private String toJson(Todo todo) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(todo);
    }

    private String toJson(List<Todo> todos) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(todos);
    }

}
