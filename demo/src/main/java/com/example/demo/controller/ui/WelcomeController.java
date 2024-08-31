package com.example.demo.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        // Model'e mesaj ekleyebilirsiniz, bu mesaj Thymeleaf şablonunda kullanılabilir.
        model.addAttribute("message", "Welcome to Spring Boot with Thymeleaf!");
        return "welcome"; // Bu, 'welcome.html' şablonunu döndürür.
    }
}