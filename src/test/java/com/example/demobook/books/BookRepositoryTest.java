package com.example.demobook.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookReposTest;

    @Test
    void findBookAuthorAndCategory() {
        Book book =  new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12), LocalDate.of(2015,05,15));

        bookReposTest.save(book);

        List<Book> books = bookReposTest.findBookAuthorAndCategory("phamsang", "Khoahoc");
        assertThat(books).asList();
    }
}