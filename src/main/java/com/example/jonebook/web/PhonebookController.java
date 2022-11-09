package com.example.jonebook.web;

import com.example.jonebook.entities.Employee;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PhonebookController {
    private final List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void Init() {
        employees.add(Employee.builder().id(0L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
        employees.add(Employee.builder().id(1L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
        employees.add(Employee.builder().id(2L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
        employees.add(Employee.builder().id(3L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
        employees.add(Employee.builder().id(4L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
        employees.add(Employee.builder().id(5L).name("John").email("John@gmail.com").phone("+XXX (XX) XXX-XX-XX").build());
    }

    @GetMapping("/")
    public String getShortVersion(Model model) {
        if (isAuthenticated()) return "redirect:/complete-edition";

        model.addAttribute("employees", employees);
        model.addAttribute("showInternalPhones", Boolean.FALSE);
        model.addAttribute("editable", Boolean.FALSE);
        return "phonebook.jsp";
    }

    @GetMapping("/complete-edition")
    public String getCompleteEdition(Model model) {
        model.addAttribute("employees", employees);
        model.addAttribute("showInternalPhones", Boolean.TRUE);
        model.addAttribute("editable", Boolean.FALSE);
        return "phonebook.jsp";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
