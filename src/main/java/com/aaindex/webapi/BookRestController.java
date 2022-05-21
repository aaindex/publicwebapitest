package com.aaindex.webapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books-rest")
public class BookRestController {
    
    @GetMapping(value = "/{id}", produces = "application/json")
    public Book getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private Book findBookById(int id) {
        Book book = new Book();
        book.setId("B-01");
        book.setAuthor("Will Smith");
        book.setIsbn("ISBN-00000000000000000001");
        book.setTitle("Who is Will Smith");

        return book;
    }
}