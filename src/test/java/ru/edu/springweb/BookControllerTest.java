package ru.edu.springweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class BookControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().is2xxSuccessful());
    }

    @Order(1)
    @Test
    public void getByIdTest() throws Exception {
        String expectResponseBody = "{\"id\":1,\"title\":\"Hobbit\",\"author\":\"Tolkien\"}";

        mvc.perform(get("/book/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> AssertionErrors.assertEquals("body", result.getResponse().getContentAsString(), expectResponseBody));
    }

    @Order(2)
    @Test
    public void saveTest() throws Exception {
        String requestBody = """
                {
                    "id": null,
                    "title": "Hobbit",
                    "author": "Tolkien"
                }
                """;

        String expectResponseBody = "{\"id\":2,\"title\":\"Hobbit\",\"author\":\"Tolkien\"}";

        mvc.perform(post("/book").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> AssertionErrors.assertEquals("body", result.getResponse().getContentAsString(), expectResponseBody));
    }

    @Order(3)
    @Test
    public void updateTest() throws Exception {
        String requestBody = """
                {
                    "id": 1,
                    "title": "War and Peace",
                    "author": "Tolstoy"
                }
                """;

        String expectResponseBody = "{\"id\":1,\"title\":\"War and Peace\",\"author\":\"Tolstoy\"}";

        mvc.perform(patch("/book").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> AssertionErrors.assertEquals("body", result.getResponse().getContentAsString(), expectResponseBody));
    }

    @Order(4)
    @Test
    public void deleteTest() throws Exception {
        mvc.perform(delete("/book/1"))
                .andExpect(status().is2xxSuccessful());
    }
}
