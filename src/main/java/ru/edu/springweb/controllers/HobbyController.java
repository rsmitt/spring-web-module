package ru.edu.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HobbyController {

    @GetMapping("/hobby")
    public String getHobbyPage(Model model) {
        model.addAttribute("hobby", "Писать книги");
        return "hobby";
    }
}
