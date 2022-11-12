package com.example.jonebook.api.v1;

import com.example.jonebook.services.EmployeeService;
import com.example.jonebook.services.dto.PublicEmployee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/employee")
public class PublicEmployeeController {

    private final EmployeeService employees;

    public PublicEmployeeController(EmployeeService employees) {
        this.employees = employees;
    }

    @GetMapping
    public List<PublicEmployee> getAll() {
        return employees.getAllPublic();
    }
}