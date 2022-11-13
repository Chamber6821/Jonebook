package com.example.jonebook.api.v1;

import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.EmployeeBuilderService;
import com.example.jonebook.services.dto.ExtendedEmployee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/extended-employee")
public class AdminEmployeeController {

    private final EmployeeRepository employees;
    private final EmployeeBuilderService builder;

    public AdminEmployeeController(EmployeeRepository employees, EmployeeBuilderService builder) {
        this.employees = employees;
        this.builder = builder;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ExtendedEmployee data) {
        return employees.save(builder.createFromData(data)).getId();
    }

    @DeleteMapping("/{ids}")
    public void deleteByIds(@PathVariable List<Long> ids) {
        try {
            employees.deleteAllById(ids);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
