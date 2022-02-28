package com.example.demobook.books;

import org.hibernate.persister.entity.Loadable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> {
            Book khoahoc = new Book(1,"10 van cau hoi vi sao?","PhamSang",
                    "Khoahoc",
                    "Sach danh cho tre em",
                    LocalDate.of(2010, Month.APRIL,12),
                    LocalDate.of(2017, Month.AUGUST, 11));

            Book khoahoc2 = new Book(2,"Cac vi sao","PhamSang",
                    "Khoahoc",
                    "Sach kham pha",
                    LocalDate.of(2010, Month.APRIL,12),
                    LocalDate.of(2015, Month.AUGUST, 11));

            repository.saveAll(
                    List.of(khoahoc, khoahoc2)
            );
        };
    }
}
