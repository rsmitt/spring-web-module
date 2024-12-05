package ru.edu.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AuthorController {
    @GetMapping({"/", "/author"})
    public String author(Model model) {
        model.addAttribute("author", "Землянигин Сергей");
        return "author";
    }

    @GetMapping("hobby")
    public String hobby(Model model) {
        List<String> hobbyList = new ArrayList<>(Arrays.asList("Путешествия", "Спорт", "Музыка"));
        model.addAttribute("hobbys", hobbyList);
        return "hobby";
    }
}
