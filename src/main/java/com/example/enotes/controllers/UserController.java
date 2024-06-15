package com.example.enotes.controllers;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.enotes.entity.Notes;
import com.example.enotes.entity.User;
import com.example.enotes.repository.UserRepository;
import com.example.enotes.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NotesService notesService;

    @ModelAttribute
    public User getUser(Principal p,Model m){
       String email= p.getName();
       User user=userRepo.findByEmail(email);
       m.addAttribute("user",user);
       return user;
    }

    @GetMapping("/addnotes")
    public String addnotes(){
        return "add_notes";
    }

    @GetMapping("/viewnotes")
    public String viewnotes(Model m,Principal p, @RequestParam(defaultValue = "0") Integer pageno){
        User user=getUser(p, m);
        Page<Notes> notes=notesService.getNotesByUser(user,pageno );
        m.addAttribute("currentPage", pageno);
        m.addAttribute("totalElements", notes.getTotalElements());
        m.addAttribute("totalPages", notes.getTotalPages());
        m.addAttribute("notesList", notes.getContent());
        return "view_notes";
    }

    @GetMapping("/editnotes/{id}")
    public String editnotes(@PathVariable int id, Model m){
        Notes notes=notesService.getNotesById(id);
        m.addAttribute("n",notes);
        return "edit_notes";
    }

    @PostMapping("/savenotes")
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m){
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p, m));

        Notes saveNotes=notesService.saveNotes(notes);
        if(saveNotes!=null){
            session.setAttribute("msg", "Notes saved successfully");
        }else{
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/user/addnotes";
    }

    @PostMapping("/updatenotes")
    public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m){
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p, m));

        Notes saveNotes=notesService.saveNotes(notes);
        if(saveNotes!=null){
            session.setAttribute("msg", "Notes updated successfully");
        }else{
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/user/viewnotes";
    }

    @GetMapping("/deletenotes/{id}")
    public String deletenotes(@PathVariable int id, HttpSession session){
        boolean f=notesService.deleteNotes(id);
        if(f){
            session.setAttribute("msg", "Notes deleted successfully");
        }else{
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/user/viewnotes";
    }
}
