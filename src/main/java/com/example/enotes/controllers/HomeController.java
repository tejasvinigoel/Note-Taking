package com.example.enotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.enotes.entity.User;
import com.example.enotes.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Autowired
    private UserService userService; //contains object of the implemented class and not the interface itself

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
        boolean f=userService.existEmailCheck(user.getEmail());
        if(f){
            session.setAttribute("msg", "Email already exists");
        }else{
        User saveUser=userService.saveUser(user);
        if(saveUser!=null){
            session.setAttribute("msg", "Registration successful");
        }else{
            session.setAttribute("msg", "Something went wrong");
        }
        }
        return "redirect:/register"; //not using--> url will still show /submit, now it goes back to /register
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
