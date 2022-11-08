package com.example.jonebook.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @GetMapping("/welcome")
    public String welcome(@RequestParam(defaultValue = "stranger") String name, Model model) {
        logger.info("Requested: /welcome");
        model.addAttribute("name", name);
        return "welcome.jsp";
    }
}
