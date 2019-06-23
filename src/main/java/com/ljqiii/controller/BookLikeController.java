package com.ljqiii.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.BookRepository;
import com.ljqiii.model.Book;
import com.ljqiii.model.BookLike;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.BookLikeService;
import com.ljqiii.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookLikeController {

    @Autowired
    BookLikeService bookLikeService;

    @Autowired
    BookService bookService;


    @GetMapping("/getmybooklike")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public ArrayList<JSONObject> getMyBookLike(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        BookLike[] bookLikes = bookLikeService.selectAllLikeBook(wxAccount.getOpenId());

        ArrayList<JSONObject> responejson = new ArrayList<>();
        for (BookLike bookLike : bookLikes) {
            JSONObject temp = new JSONObject();
            Book tempbook = bookService.getBook(bookLike.getId());

            temp.put("booklikeid", bookLike.getId());
            if (tempbook != null) {
                temp.put("bookname", tempbook.getName());
            } else {
                temp.put("bookname", "");
            }
            temp.put("bookcoverimgurl", "/bookcover/" + tempbook.getBookcoverimg());
            temp.put("bookdescription", tempbook.getDescription());
        }
        return responejson;
    }



}
