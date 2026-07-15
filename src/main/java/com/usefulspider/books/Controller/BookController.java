package com.usefulspider.books.Controller;

import com.usefulspider.books.entity.Book;
import org.springframework.web.bind.annotation.*;

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
    public List<Book> getBooks(@RequestParam(required = false) String Category){
        if (Category == null){
            return books;
        }
        return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(Category)).toList();
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

    @PostMapping("api/book")
    public void createBook(@RequestBody Book newBook){
//        for(Book book:books){
//            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
//                return;
//            }
//        }
//        books.add(newBook);
        boolean isNewBook = books.stream().noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if(isNewBook){
            books.add(newBook);
        }

    }

    @PutMapping("api/book/title")
    public void updateBook(@PathVariable String title, @RequestBody Book updateBook){
        for (int i=0;i<books.size();i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                books.set(i,updateBook);
            return;
            }
        }
    }
}
