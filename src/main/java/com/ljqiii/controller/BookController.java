package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.Book;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookController {

    @Autowired
    BookService bookService;


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

    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @GetMapping("/getalllikebook")
    public ArrayList<JSONObject> getalllikebook(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        Book[] books = bookService.selectAllBookLikeByOpenid(wxAccount.getOpenId());
        return books2json(books);
    }
}
