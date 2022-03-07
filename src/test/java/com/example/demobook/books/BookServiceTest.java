package com.example.demobook.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
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
//        Book captrueBook = bookArgumentCaptor.getValue();
//        assertThat(captrueBook).isEqualTo(book);
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
        verify(bookRepository).existsById(1);
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
//        List<Book> books = List.of(book,book2);
//
//        //given(bookRepository.existsById(1)).willReturn(true);
//        assertThatThrownBy(()->bookUnderService.deleteBook(1))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessageContaining("Book with id "+ 1 + " does not exists");
//
       List<Book> bookAfterDelete = bookUnderService.getBook();
//
//        assertThat(books).isNotEqualTo(bookAfterDelete);
        System.out.println(bookAfterDelete.size());
    }

    @Test
    void updateBooks() {
        //given
        Book book =  new Book(1, "The war", "phamsang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));
        Book book2 = new Book(2,"Cac vi sao","PhamSang",
                "Khoahoc",
                "Sach kham pha",
                LocalDate.of(2010, Month.APRIL,12),
                LocalDate.of(2015, Month.AUGUST, 11));
        bookRepository.saveAll(List.of(book,book2));

        Book bookAfterUpdate =  new Book(1, "The War 2", "pham chi sang",
                "Khoahoc", "quan su",
                LocalDate.of(2010,05,12),
                LocalDate.of(2015,05,15));

    }

    @Test
    void getABookId() {

    }

    @Test
    void getBookAuthor_Category() {

    }
}