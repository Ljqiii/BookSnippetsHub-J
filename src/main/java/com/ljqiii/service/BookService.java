package com.ljqiii.service;

import com.ljqiii.dao.BookRepository;
import com.ljqiii.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public boolean addBook(Book book){
        bookRepository.insertByBook(book);
        return true;
    }


}
