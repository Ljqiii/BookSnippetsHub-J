package com.ljqiii.service;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.*;
import com.ljqiii.model.BackgroundImage;
import com.ljqiii.model.Book;
import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
public class FeedService {

    @Autowired
    BackgroundImageRepository backgroundImageRepository;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    FeedLikeRepository feedLikeRepository;

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
            book = new Book();
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


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> feedResopne(Feed[] feeds) {

        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        for (Feed feed : feeds) {
            JSONObject temp = new JSONObject();


            BackgroundImage backgroundImage = backgroundImageRepository.findById(feed.getBackgroundimageid());
            if (backgroundImage != null) {
                temp.put("backgroundmageurl", "/bgimg/" + backgroundImage.getFilename());
            } else {
                temp.put("backgroundmageurl", "");
            }


            temp.put("bookname", feed.getBookid());
            Date date = feed.getFeedtime();

            temp.put("time", feed.getFeedtime());

            Book book = bookRepository.findById(feed.getBookid());
            if (book != null) {
                temp.put("bookname", book.getName());
                temp.put("bookid", book.getId());
            } else {
                temp.put("bookname", "");
                temp.put("bookid", "");
//                continue;
            }
            temp.put("bookcontent", feed.getBookcontent());
            temp.put("bookcomment", feed.getBookcomment());
            temp.put("id", feed.getId());

            String fromopenid = feed.getFromopenid();
            WxAccount wxAccount = wxAccountRepository.findByOpenid(fromopenid);
            if (wxAccount != null) {
                temp.put("nickname", wxAccount.getNickName());
                temp.put("avatarUrl", wxAccount.getAvatarUrl());
            } else {
                temp.put("nickname", "");
                temp.put("avatarUrl", "");
//                continue;

            }


            int likecount = feedLikeRepository.selectFeedLikeCount(feed.getId());
            int isthisuserlike = feedLikeRepository.selectCountByFeedIdOpenid(feed.getId(), fromopenid);

            temp.put("likecount", likecount);

            if (isthisuserlike == 0) {
                temp.put("isliked", false);
            } else {
                temp.put("isliked", true);
            }

            jsonObjects.add(temp);
        }
        return jsonObjects;

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedRand(int count, ArrayList<Integer> notin) {
        Feed[] feeds = feedRepository.findFeedRand(count, notin);
        return feedResopne(feeds);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedByBookid(int count, ArrayList<Integer> notin, int bookid) {
        Feed[] feeds = feedRepository.findFeedByBookid(count, notin, bookid);

        return feedResopne(feeds);

    }
}
