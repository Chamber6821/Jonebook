package com.example.jonebook.api.v1;

import com.example.jonebook.entities.Department;
import com.example.jonebook.repositories.DepartmentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/department")
public class SearchDepartmentController {

    private final DepartmentRepository departments;

    public SearchDepartmentController(DepartmentRepository departments) {
        this.departments = departments;
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String name) {
        return departments.findByNameStartingWithOrderByName(name).stream()
                .map(Department::getName)
                .toList();
    }
}
