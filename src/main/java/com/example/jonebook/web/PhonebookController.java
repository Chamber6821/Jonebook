package com.example.jonebook.web;

import com.example.jonebook.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhonebookController {
    private final EmployeeService employees;

    public PhonebookController(EmployeeService employees) {
        this.employees = employees;
    }

    @GetMapping({"", "/"})
    public String getShortVersion(Model model) {
        model.addAttribute("employees", employees.getAllExtended());
        return "phonebook.jsp";
    }
}
