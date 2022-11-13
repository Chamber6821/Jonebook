package com.example.jonebook.web;

import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.ExtendedEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhonebookController {
    private final EmployeeRepository employees;

    public PhonebookController(EmployeeRepository employees) {
        this.employees = employees;
    }

    @GetMapping({"", "/"})
    public String getShortVersion(Model model) {
        model.addAttribute("employees", employees.findAll().stream().map(ExtendedEmployee::new).toList());
        return "phonebook.jsp";
    }
}
