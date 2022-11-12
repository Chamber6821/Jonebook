package com.example.jonebook.api.v1;

import com.example.jonebook.services.EmployeeSearchService;
import com.example.jonebook.services.EmployeeService;
import com.example.jonebook.services.dto.EmployeeCriteria;
import com.example.jonebook.services.dto.ExtendedEmployer;
import com.example.jonebook.services.dto.PublicEmployee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employees;
    private final EmployeeSearchService searchService;

    public EmployeeController(EmployeeService employees, EmployeeSearchService searchService) {
        this.employees = employees;
        this.searchService = searchService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<PublicEmployee> getAll() {
        return employees.getAllPublic();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{ids}")
    public List<ExtendedEmployer> getByIds(@PathVariable List<Long> ids) {
        return employees.getByIdsExtended(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ids}")
    public void deleteByIds(@PathVariable List<Long> ids) {
        try {
            employees.removeByIds(ids);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ExtendedEmployer data) {
        return employees.add(data);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/extended")
    public List<ExtendedEmployer> getAllExtended() {
        return employees.getAllExtended();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public List<ExtendedEmployer> search(@RequestBody EmployeeCriteria criteria) {
        return searchService.search(criteria);
    }
}