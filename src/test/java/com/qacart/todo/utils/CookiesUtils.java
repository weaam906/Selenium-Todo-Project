/* ------------------------------------------------------- -
  - Date: 28th.Dec.23                                      -
  - Auth: We'am Othman                                     -
  - Desc: Convert API cookies to selenium cookies in order -
  -       to save it and use it in logging in the website  -                                        -
  - ------------------------------------------------------ - */

package com.qacart.todo.utils;

import io.restassured.http.Cookie;
import java.util.ArrayList;
import java.util.List;

//1- This method takes list of restAssuredCookie and loop on it,
//2- then convert each cookie in seleniumCookie,
//3- then add this seleniumCookie to a list of seleniumCookie
//4- then return this list

public class CookiesUtils {
    public static List<org.openqa.selenium.Cookie> convertRestAssuredToSeleniumCookie(List<Cookie> restAssuredCookies){
        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();
        for (Cookie cookie:restAssuredCookies){
            org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(cookie.getName(),cookie.getValue());
            seleniumCookies.add(seleniumCookie);
        }
        return seleniumCookies;
    }
}
