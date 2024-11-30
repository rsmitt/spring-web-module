package ru.edu.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        model.addAttribute("author", "Чарльз Майк Паланик");
        return "profile";
    }
}
