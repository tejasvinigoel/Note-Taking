package com.example.enotes.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.enotes.entity.User;
import com.example.enotes.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    public void getUser(Principal p,Model m){
       String email= p.getName();
       User user=userRepo.findByEmail(email);
       m.addAttribute("user",user);
    }

    @GetMapping("/addnotes")
    public String addnotes(){
        return "add_notes";
    }

    @GetMapping("/viewnotes")
    public String viewnotes(){
        return "view_notes";
    }

    @GetMapping("/editnotes")
    public String editnotes(){
        return "edit_notes";
    }
}
