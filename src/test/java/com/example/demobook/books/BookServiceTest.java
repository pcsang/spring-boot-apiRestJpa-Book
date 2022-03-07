package com.example.demobook.books;

import org.apache.el.parser.AstSetData;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookUnderService;

    @BeforeEach
    void setUp() {
        bookUnderService = new BookService(bookRepository);
    }


    @Test
    void getBook() {
        bookUnderService.getBook();
        verify(bookRepository).findAll();
    }

    @Test
    @Disabled
    void addNewBook() {
        Book book =  new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        bookUnderService.addNewBook(book);
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
        Book book2 =  new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        bookRepository.saveAll(List.of(book, book2));

        assertThatThrownBy(()->bookUnderService.deleteBook(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Book with id "+ 1 + " does not exists");

    }

    @Test
    void deleteBookEqual() {
        //given
        Book book =  new Book(1, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        bookUnderService.addNewBook(book);
        Book book2 =  new Book(2, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        bookUnderService.addNewBook(book2);
        List<Book> books = List.of(book);
    }

    @Test
    void updateBooks() {
        //given
        Book book =  new Book(1, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));

        Book bookAfterUpdate =  new Book(1, "The war 2", "pham chi sang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
    }

    @Test
    void getABookId() {
        given(bookRepository.existsById(1)).willReturn(true);
        when(bookUnderService.getABookId(1)).thenReturn(Optional.of(new Book(1, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010, 05, 12),
                LocalDate.of(2015, 05, 15))));
        assertThat(bookRepository.findById(1)).isEqualTo(bookUnderService.getABookId(1));
    }

    @Test
    void getBookAuthor_Category() {

    }
}