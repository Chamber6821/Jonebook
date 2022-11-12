package com.example.jonebook.api.v1;

import com.example.jonebook.services.EmployeeService;
import com.example.jonebook.services.dto.ExtendedEmployer;
import com.example.jonebook.services.dto.PublicEmployee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employees;

    public EmployeeController(EmployeeService employees) {
        this.employees = employees;
    }

    @GetMapping
    public List<PublicEmployee> getAll() {
        return employees.getAllPublic();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/extended")
    public List<ExtendedEmployer> getAllExtended() {
        return employees.getAllExtended();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{ids}")
    public List<ExtendedEmployer> getByIds(@PathVariable List<Long> ids) {
        return new ArrayList<>();
    }
}