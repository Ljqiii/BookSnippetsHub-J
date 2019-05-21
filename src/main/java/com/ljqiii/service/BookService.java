package com.ljqiii.service;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.BookRepository;
import com.ljqiii.model.Book;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public boolean addBook(Book book) {
        bookRepository.insertByBook(book);
        return true;
    }

    public Book[] getAllBook() {
        return bookRepository.findAllBook();
    }



    public Book[] getLikeBook(String openid){
        return null;
    }

    public Book[] getLikeBook(WxAccount wxAccount){
        return null;
    }

    public Book getBook(int bookid){

        return bookRepository.findById(bookid);
    }

    public Book[] selectAllBookLikeByOpenid(String openid){
        return bookRepository.selectAllBookLikeByOpenid(openid);
    }


}
