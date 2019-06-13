package com.ljqiii.utils;

import java.util.regex.Pattern;

public class AccountTypeUtil {

    public static int WXACCOUNT = 1;
    public static int UUIDACCOUNT = 2;
    public static int OTHER = -1;


    public static int judge(String openid) {
        //uuid example      : 3d6185db-8ab8-42ce-bd27-a906605a1e83
        //openid example    : owh1O5ckqseCFh8ZDmT6mpA7zpPQ

        String uuidpattern = "[\\da-z]{8}-[\\da-z]{4}-[\\da-z]{4}-[\\da-z]{4}-[\\da-z]{12}";
        String wxaccountpattern = "[a-z]{28}";

        if (Pattern.matches(uuidpattern, openid)) {
            return UUIDACCOUNT;
        } else if (Pattern.matches(wxaccountpattern, openid)) {
            return WXACCOUNT;
        }
        return OTHER;
    }
}
