package com.example.demobook.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBook(){
        return bookService.getBook();
    }

    @PostMapping
    public void registerNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{idBook}")
    public void deleteBook(@PathVariable("idBook") int idBook){
        bookService.deleteBook(idBook);
    }

    @PutMapping(path = "{idBook}")
    public void updateBook(@PathVariable("idBook") int idBook,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String author){
        bookService.updateBooks(idBook, name, author);
    }
}
