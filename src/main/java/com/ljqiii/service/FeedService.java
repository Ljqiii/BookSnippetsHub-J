package com.ljqiii.service;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.BookRepository;
import com.ljqiii.dao.FeedRepository;
import com.ljqiii.model.Book;
import com.ljqiii.model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    FeedRepository feedRepository;

    public boolean addSelfFeed(JSONObject requestjson, String openid) {
        return addFeed(requestjson, openid, openid);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addFeed(JSONObject requestjson, String openid, String fromopenid) {
        Book book;
        book = bookRepository.findByName(requestjson.getString("bookname"));
        if (book == null) {
            book=new Book();
            book.setName(requestjson.getString("bookname"));
            bookRepository.insertByBook(book);
        }

        Feed feed = new Feed();

        feed.setOpenid(openid);
        feed.setFromopenid(fromopenid);
        feed.setBookid(book.getId());
        feed.setBookcontent(requestjson.getString("bookcontent"));
        feed.setBookcomment(requestjson.getString("bookcomment"));
        feed.setBackgroundimageid(requestjson.getIntValue("bgimgid"));

        feedRepository.insertByFeed(feed);
        return true;
    }
}
