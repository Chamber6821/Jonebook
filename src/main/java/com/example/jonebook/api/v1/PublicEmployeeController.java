package com.example.jonebook.api.v1;

import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.PublicEmployee;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/employee")
public class PublicEmployeeController {

    public final int PUBLIC_PAGE_SIZE = 100;

    private final EmployeeRepository employees;

    public PublicEmployeeController(EmployeeRepository employees) {
        this.employees = employees;
    }

    @GetMapping
    public List<PublicEmployee> getAll(@RequestParam(defaultValue = "100") int pageSize,
                                       @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = Pageable.ofSize(Math.min(pageSize, PUBLIC_PAGE_SIZE)).withPage(page);
        return employees.findAll(pageable).stream()
                .map(PublicEmployee::new)
                .toList();
    }
}