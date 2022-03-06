package com.example.demobook.books;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    public void getListBook() throws Exception {
        when(bookService.getBook()).
                thenReturn(List.of(new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15))));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/book"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("The war"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("phamsang"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("Khoahoc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].desciption").value("quan su"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createDate").value(LocalDate.of(2010,05,12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].updateDate").value(LocalDate.of(2015,05,15)));
    }
}
