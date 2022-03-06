package com.example.demobook.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
//    private AutoCloseable autoCloseable;
    private BookService bookUnderService;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        bookUnderService = new BookService(bookRepository);
    }

    @Test
    void getBook() {
        //when
        bookUnderService.getBook();
        //then
        verify(bookRepository).findAll();

    }

    @Test
    @Disabled
    void addNewBook() {
        //given
        Book book =  new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        //when
        bookUnderService.addNewBook(book);
        //then
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book captrueBook = bookArgumentCaptor.getValue();
        assertThat(captrueBook).isEqualTo(book);
    }

    @Test
    @Disabled
    void deleteBook() {
        //given
        Book book =  new Book(1, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        bookUnderService.addNewBook(book);

        //given(bookRepository.existsById(1)).willReturn(true);
        assertThatThrownBy(()->bookUnderService.deleteBook(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Book with id "+ 1 + " does not exists");
//        bookUnderService.deleteBook(1);
//        verify(bookRepository).deleteById(1);
    }

    @Test
    void updateBooks() {
    }

    @Test
    void getABookId() {
    }

    @Test
    void getBookAuthor_Category() {
    }
}