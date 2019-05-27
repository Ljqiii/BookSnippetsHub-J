package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.Book;
import com.ljqiii.model.BookLike;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.BookLikeService;
import com.ljqiii.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookLikeService bookLikeService;


    ArrayList<JSONObject> books2json(Book[] books) {
        ArrayList<JSONObject> responejson = new ArrayList<>();
        for (Book book : books) {
            JSONObject temp = new JSONObject();

            temp.put("id", book.getId());
            temp.put("name", book.getName());
            temp.put("bookcoverimg", "/bookcover/" + book.getBookcoverimg());
            temp.put("description", book.getDescription());
            responejson.add(temp);
        }
        return responejson;
    }

    @GetMapping("/getallbook")
    public ArrayList<JSONObject> getAllBook() {
        Book[] books = bookService.getAllBook();
        return books2json(books);
    }

    @PostMapping("/getbook")
    public JSONObject getBook(@RequestBody JSONObject requestjson) {

        int bookid = requestjson.getInteger("bookid");
        Book book = bookService.getBook(bookid);
        JSONObject responejson = new JSONObject();

        responejson.put("id", book.getId());
        responejson.put("name", book.getName());
        responejson.put("description", book.getDescription());
        responejson.put("bookcoverimgurl", "/bookcover/" + book.getBookcoverimg());

        return responejson;
    }


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @GetMapping("/getalllikebook")
    public ArrayList<JSONObject> getalllikebook(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        Book[] books = bookService.selectAllBookLikeByOpenid(wxAccount.getOpenId());
        return books2json(books);
    }



    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/islike")
    public JSONObject islike(WxAuthenticationToken wxAuthenticationToken,@RequestBody JSONObject requestjson){

        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        JSONObject resopnejson=new JSONObject();
        resopnejson.put("islike",bookLikeService.islike(wxAccount.getOpenId(),requestjson.getInteger("bookid")));
        return resopnejson;
    }
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/dislikebook")
    public JSONObject dislikefeed(WxAuthenticationToken wxAuthenticationToken,@RequestBody JSONObject requestjson){

        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        JSONObject resopnejson=new JSONObject();

        resopnejson.put("isok",bookLikeService.deleteLikeBook(wxAccount.getOpenId(),requestjson.getInteger("bookid")));
        return resopnejson;
    }


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/likebook")
    public JSONObject likefeed(WxAuthenticationToken wxAuthenticationToken,@RequestBody JSONObject requestjson){

        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        JSONObject resopnejson=new JSONObject();

        resopnejson.put("isok",bookLikeService.addLikeBook(wxAccount.getOpenId(),requestjson.getInteger("bookid")));
        return resopnejson;
    }







}
