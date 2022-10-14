package com.sso.login.controller;

import com.sso.login.pojo.User;
import com.sso.login.utils.LogCacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false, defaultValue = "") String target,
                          HttpSession session,
                          @CookieValue(required = false, value = "TOKEN") Cookie cookie){
        if(StringUtils.isEmpty(target)){
            target = "login.codeshop.com:9010";
        }
        if(cookie != null){
            String value = cookie.getValue();
            User user = LogCacheUtil.loginUser.get(value);
            if(user != null){
                return "redirect:" + target;
            }
        }
        session.setAttribute("target",target);
        return "login";
    }

}
