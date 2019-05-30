package com.ljqiii.service;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.*;
import com.ljqiii.model.*;
import com.ljqiii.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FeedService {

    @Autowired
    BookService bookService;

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

    @Autowired
    FeedCommentRepository feedCommentRepository;

    @Autowired
    FollowRepository followRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


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
    public ArrayList<JSONObject> getfollowuserfeeds(WxAccount wxAccount,String from) {
        if (wxAccount == null) {
            return null;
        } else {
            String openid = wxAccount.getOpenId();
            String[] followsopenid = followRepository.selectAllFollowsByOpenid(openid);
            ArrayList<Feed> feeds = new ArrayList<>();

            for (String followopenid : followsopenid) {
                Feed[] temp = feedRepository.findByOpenid(followopenid);
                for (Feed feed : temp) {
                    feeds.add(feed);
                }
            }
            return feedResopne(feeds,from,wxAccount);

        }

    }


    public ArrayList<JSONObject> feedResopne(ArrayList<Feed> feeds, String from, WxAccount wxaccount) {
        Feed[] feeds1 = (Feed[]) feeds.toArray(new Feed[feeds.size()]);
        return feedResopne(feeds1, from, wxaccount);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> feedResopne(Feed[] feeds, String from, WxAccount wxaccount) {

        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        for (Feed feed : feeds) {
            JSONObject temp = new JSONObject();


            BackgroundImage backgroundImage = backgroundImageRepository.findById(feed.getBackgroundimageid());
            if (backgroundImage != null) {
                temp.put("backgroundmageurl", "/bgimg/" + backgroundImage.getFilename());
            } else {
                temp.put("backgroundmageurl", "");
            }


            temp.put("from", from);
            temp.put("bookname", feed.getBookid());
            Date date = feed.getFeedtime();


            temp.put("time", simpleDateFormat.format(feed.getFeedtime()));


            //书籍
            Book book = bookRepository.findById(feed.getBookid());
            if (book != null) {
                temp.put("bookname", book.getName());
                temp.put("bookid", book.getId());
            } else {
                temp.put("bookname", "");
                temp.put("bookid", "");
//                continue;
            }
            temp.put("id", feed.getId());
            temp.put("bookcontent", feed.getBookcontent());
            temp.put("bookcomment", feed.getBookcomment());


            //微信用户
            String fromopenid = feed.getFromopenid();


            WxAccount feedwxaccount = wxAccountRepository.findByOpenid(feed.getOpenid());
            if (feedwxaccount != null) {
                temp.put("nickname", feedwxaccount.getNickName());
                temp.put("avatarUrl", feedwxaccount.getAvatarUrl());
                //发表feed的用户id
                temp.put("userid", feedwxaccount.getId());
            } else {
                temp.put("userid", -1);
                temp.put("nickname", "");
                temp.put("avatarUrl", "");
//                continue;

            }

            //是否关注

            int isfollow = 0;
            if (wxaccount != null) {
                isfollow = followRepository.selectisfollow(wxaccount.getOpenId(), feed.getOpenid());
            }
            if (isfollow != 0) {
                temp.put("isfollow", true);
            } else {
                temp.put("isfollow", false);
            }


            //评论
//            int userfeedcommentcount =0;// feedCommentRepository.usercounts(feed.getId(), wxaccount.getOpenId());
//            if (userfeedcommentcount != 0) {
//                temp.put("iscomment", true);
//            } else {
//                temp.put("iscomment", false);
//            }
            int feedcommentaccount = feedCommentRepository.count(feed.getId());
            temp.put("commentcount", feedcommentaccount);

            //转载
            if (feed.getFromopenid().equals(feed.getOpenid())) {
                temp.put("isforward", false);
            } else {
                temp.put("isforward", true);
            }


            //喜欢

            int isthisuserlike;
            int likecount = feedLikeRepository.selectFeedLikeCount(feed.getId());

            if (wxaccount != null) {
                isthisuserlike = feedLikeRepository.selectCountByFeedIdOpenid(feed.getId(), wxaccount.getOpenId());
            } else {
                isthisuserlike = 0;
            }

            temp.put("likecount", likecount);

            if (isthisuserlike != 0) {
                temp.put("isliked", true);
            } else {
                temp.put("isliked", false);
            }

            //折叠
            temp.put("isFolded", true);


            jsonObjects.add(temp);
        }
        return jsonObjects;

    }


//    @Transactional(propagation = Propagation.REQUIRED)
//    public ArrayList<JSONObject> getFeedByBook(int count, ArrayList<Integer> notin,int bookid) {
//        Feed[] feeds=feedRepository.findFeedByBookid(10,notin,bookid);
//        return feedResopne(feeds,"bookid");
//    }


    public ArrayList<JSONObject> getFeedByBookList(int count, ArrayList<Integer> notin, String openid) {

        return null;
        //
//        if()
//        ArrayList<JSONObject> jo
//
//        Book[] books = bookService.selectAllBookLikeByOpenid(openid);
//        Feed[] feeds=feedRepository.

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getAllMyFeed(WxAccount wxAccount) {
        String openid=wxAccount.getOpenId();
        Feed[] feeds=feedRepository.findByOpenid(openid);

        return feedResopne(feeds,"my",wxAccount);
    }


        @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedRand(int count, ArrayList<Integer> notin, WxAccount wxAccount) {
        Feed[] feeds = feedRepository.findFeedRand(count, notin);
        return feedResopne(feeds, "recommand", wxAccount);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedByBookid(int count, ArrayList<Integer> notin, int bookid, WxAccount wxAccount) {
        Feed[] feeds = feedRepository.findFeedByBookid(count, notin, bookid);
        return feedResopne(feeds, "book", wxAccount);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedByOpenid(int count, ArrayList<Integer> notin, String openid) {
        Feed[] feeds = feedRepository.findFeedByOpenid(count, notin, openid);
        WxAccount wxAccount = wxAccountRepository.findByOpenid(openid);
        return feedResopne(feeds, "openid", wxAccount);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedsbyId(int feedids[], WxAccount wxAccount) {

        Feed[] feeds = new Feed[feedids.length];
        int i = 0;
        for (int feedid : feedids) {
            feeds[i++] = feedRepository.findById(feedid);
        }
        return feedResopne(feeds, "myfav", wxAccount);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ArrayList<JSONObject> getFeedById(int bookid, WxAccount wxAccount) {
        Feed[] feeds = new Feed[1];
        feeds[0] = feedRepository.findById(bookid);
        return feedResopne(feeds, "feedid", wxAccount);
    }


    public boolean forwardFeed(String openid, int feedid) {
        Feed feed = feedRepository.findById(feedid);
        feed.setOpenid(openid);
        feedRepository.insertByFeed(feed);
        return true;
    }

    public boolean disForwardFeed(String openid, int feedid) {
        Feed feed = feedRepository.findByIdAndOpenid(openid, feedid);

        if (feed != null) {
            feedRepository.deleteById(feed.getId());
            return true;
        }
        return false;
    }

}
