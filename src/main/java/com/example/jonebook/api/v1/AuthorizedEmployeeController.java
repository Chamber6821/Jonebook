package com.example.jonebook.api.v1;


import com.example.jonebook.services.EmployeeSearchService;
import com.example.jonebook.services.EmployeeService;
import com.example.jonebook.services.dto.EmployeeCriteria;
import com.example.jonebook.services.dto.ExtendedEmployee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/extended-employee")
public class AuthorizedEmployeeController {

    private final EmployeeService employees;
    private final EmployeeSearchService searchService;

    public AuthorizedEmployeeController(EmployeeService employees, EmployeeSearchService searchService) {
        this.employees = employees;
        this.searchService = searchService;
    }

    @GetMapping
    public List<ExtendedEmployee> getAllExtended() {
        return employees.getAllExtended();
    }

    @GetMapping("/{ids}")
    public List<ExtendedEmployee> getByIds(@PathVariable List<Long> ids) {
        return employees.getByIdsExtended(ids);
    }

    @GetMapping("/search")
    public List<ExtendedEmployee> search(@RequestBody EmployeeCriteria criteria) {
        return searchService.search(criteria);
    }
}
