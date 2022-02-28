package com.example.demobook.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(int id) {
       boolean exists = bookRepository.existsById(id);
       if(!exists){
           throw new IllegalStateException("Book with id "+ id + " does not exists");
       }
       bookRepository.deleteById(id);
    }

    @Transactional
    public void updateBooks(int idBook, String name, String author) {
        Book book = bookRepository.findById(idBook).
                orElseThrow(()-> new IllegalStateException("Book with id " +idBook+ " does not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(book.getName(), name)){
            book.setName(name);
        }
        if(author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)){
            book.setAuthor(author);
        }
    }
}
