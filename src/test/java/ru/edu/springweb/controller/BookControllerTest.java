package ru.edu.springweb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.edu.springweb.config.WebAppConfig;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class)
class BookControllerTest {

    private final String URL = "/api/books";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getBooksTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void getBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void notFoundBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content("{\"id\": 4, \"title\": \"The Clockwork Orange\", \"author\": \"A.Burgess\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/1")
                .content("{\"title\": \"The Da Vinci Code\", \"author\": \"Dan Brown\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
