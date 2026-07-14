package com.usefulspider.books.Controller;

import com.usefulspider.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books= new ArrayList<>();

    public BookController() {
        initializeBook();
    }

    private void initializeBook(){
        books.addAll(List.of(
                new Book("Title one","author one","sci"),
                new Book("Title two","author two","sci")
        ));
    }

    @GetMapping("api/book")
    public List<Book> getBooks(){
        return books;
    }

    @GetMapping("api/book/{title}")
    public Book getBookByTittle(@PathVariable String title){
//        for(Book book:books){
//            if(book.getTitle().equalsIgnoreCase(title)) {
//                return book;
//            }
//        }return null;
        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }
}
