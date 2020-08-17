package com.clark.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object logined = request.getSession().getAttribute("loginUser");
        if (logined == null) {
            request.setAttribute("msg", "Please login.");
            request.getRequestDispatcher("/user/user-login.html").forward(request, response);
            return false;
        }else{
            return true;
        }
    }


}
