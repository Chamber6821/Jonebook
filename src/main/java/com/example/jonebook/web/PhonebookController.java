package com.example.jonebook.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhonebookController {

    @PreAuthorize("permitAll()")
    @GetMapping
    public String getPublic() {
        return "book.jsp";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String edit() {
        return "edit.jsp";
    }
}
