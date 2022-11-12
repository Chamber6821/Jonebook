package com.example.jonebook.api.v1;

import com.example.jonebook.services.EmployeeService;
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

    private final EmployeeService employees;

    public AdminEmployeeController(EmployeeService employees) {
        this.employees = employees;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ExtendedEmployee data) {
        return employees.add(data);
    }

    @DeleteMapping("/{ids}")
    public void deleteByIds(@PathVariable List<Long> ids) {
        try {
            employees.removeByIds(ids);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
