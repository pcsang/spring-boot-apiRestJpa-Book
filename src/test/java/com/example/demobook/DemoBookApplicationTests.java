package com.example.demobook;

import com.example.demobook.books.Book;
import com.example.demobook.books.BookRepository;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class DemoBookApplicationTests {

	@Autowired
	BookRepository bookRepository;

	@Test
	public void testCreate(){
		Book book = new Book();
		book.setId(1);
		book.setName("the war");
		book.setAuthor("phamsang");
		book.setCategory("chinetranh");
		book.setDesciption("sach hay");
		book.setCreateDate(LocalDate.of(2010,05,12));
		book.setUpdateDate(LocalDate.of(2015,01,16));
		Assert.assertNotNull(bookRepository.findById(1));
	}


}
