package com.example.demobook.bookTest;

import com.example.demobook.books.Book;
import com.example.demobook.books.BookController;
import com.example.demobook.books.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@DataJpaTest
public class BookControllTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    BookService bookService;

    @Test
    public void testgetBook() throws Exception{
        List<Book> books = new ArrayList<>();
        books.add(
                new Book(2, "The war", "phamsang",
                        "Khoahoc", "quan su",
                        LocalDate.of(2010,05,12), LocalDate.of(2015,05,15)));
//        books.add(new Book(1, "10000 cau hoi vi sao", "phamsang",
//                "Khoahoc", "sach tre em",
//                LocalDate.of(2010,05,12), LocalDate.of(2015,05,15)));

        when(bookService.getBooks()).thenReturn(books);
        String url = "/api/v1/book";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("The war"))
                .andExpect(jsonPath("$.author").value("phamsang"))
                .andExpect(jsonPath("$.category").value("Khoahoc"))
                .andExpect(jsonPath("$.desciptoin").value("quan su"))
                .andExpect(jsonPath("$.createDate").value(LocalDate.of(2010,05,12)))
                .andExpect(jsonPath("$.updateDate").value(LocalDate.of(2015,05,15)))
                .andExpect(status().isOk());
    }

}
