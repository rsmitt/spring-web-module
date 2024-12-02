package ru.edu.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("author", "Пушкин Александр Сергеевич");
        return "profile";
    }

}
