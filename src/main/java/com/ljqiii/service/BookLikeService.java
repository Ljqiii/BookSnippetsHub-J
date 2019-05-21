package com.ljqiii.service;

import com.ljqiii.dao.BookLikeRepository;
import com.ljqiii.model.Book;
import com.ljqiii.model.BookLike;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLikeService {

    @Autowired
    BookLikeRepository bookLikeRepository;

    public BookLike[] selectAllLikeBook(String openid) {
        return bookLikeRepository.findAllByOpenid(openid);
    }

    public boolean addLikeBook(String openid, int bookid) {
        bookLikeRepository.insert(openid, bookid);
        return true;
    }

    public boolean addLikeBook(WxAccount wxAccount, Book book) {
        bookLikeRepository.insert(wxAccount.getOpenId(), book.getId());
        return true;
    }

    public boolean addLikeBook(String openid, Book book) {
        bookLikeRepository.insert(openid, book.getId());
        return true;
    }

    public boolean addLikeBook(WxAccount wxAccount, int bookid) {
        bookLikeRepository.insert(wxAccount.getOpenId(), bookid);
        return true;
    }

    public boolean deleteLikeBook(WxAccount wxAccount, int bookid) {
        bookLikeRepository.delete(wxAccount.getOpenId(), bookid);
        return true;
    }

    public boolean deleteLikeBook(String openid, int bookid) {
        bookLikeRepository.delete(openid, bookid);
        return true;
    }

    public boolean islike(String openid, int bookid) {
        if (bookLikeRepository.count(openid, bookid) != 0) {
            return true;
        } else {
            return false;
        }
    }
}
