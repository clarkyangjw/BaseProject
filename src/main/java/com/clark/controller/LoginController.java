package com.clark.controller;

import com.clark.pojo.User;
import com.clark.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
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

    @GetMapping({"/"})
    public String toLogin(){
        return "/user/user-login";
    }

    @GetMapping("/index")
    public String index(){
        return "/index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String loginUsername,
            @RequestParam("password") String loginPassword,
            boolean rememberMe,
            Model model,
            HttpSession session) {
        //System.out.println(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUsername, loginPassword, rememberMe);

        try{
            subject.login(token);
            User user = userService.getUserByUserame(loginUsername);
            //System.out.println(user);
            session.setAttribute("loginUser", user);
            return "redirect:/index";

        } catch (UnknownAccountException uae) {
            //log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("msg","Username does not exist.");
        } catch (IncorrectCredentialsException ice) {
            //log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("msg","Password is incorrect.");
        } catch (LockedAccountException lae) {
            //log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    //"Please contact your administrator to unlock it.");
            model.addAttribute("msg","Account is locked.");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            model.addAttribute("msg","Unknown error.");
        }
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
