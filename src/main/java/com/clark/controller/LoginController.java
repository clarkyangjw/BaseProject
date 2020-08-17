package com.clark.controller;

import com.clark.pojo.User;
import com.clark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String loginUsername,
            @RequestParam("password") String loginPassword,
            Model model,
            HttpSession session) {
        //System.out.println("进入了login controller");
        User user = userService.getUserByUserame(loginUsername);
        if(user != null){
            if(user.getPassword().equals(loginPassword)){
                session.setAttribute("loginUser", user);
                model.addAttribute("user", user);
                return "/index";
            }
        }
        model.addAttribute("msg", "Username or Password incorrect.");
        return "/user/user-login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/user/user-login";

    }

    @GetMapping("/register")
    public String toRegister(){
        return "/user/user-register";
    }

    @PostMapping("/addUser")
    public String addUser(User user,@RequestParam("password2") String password2, Model model){
        String msg = userService.addUser(user, password2);
        model.addAttribute("msg", msg);
        if(msg.equals("Successfully Created Account.")){
            return "/user/user-login";
        }
        return "/user/user-register";
    }

}
