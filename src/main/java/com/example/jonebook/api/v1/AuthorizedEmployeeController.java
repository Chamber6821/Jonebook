package com.example.jonebook.api.v1;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import com.example.jonebook.services.dto.ExtendedEmployee;
import com.example.jonebook.services.search.SearchEmployee;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/extended-employee")
@AllArgsConstructor
public class AuthorizedEmployeeController {

    private final EmployeeRepository employees;
    private final SearchEmployee searchEmployee;

    @GetMapping
    public List<ExtendedEmployee> getAll(@RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return toExtendedList(employees.findAll(pageable));
    }

    @GetMapping("/{ids}")
    public List<ExtendedEmployee> getByIds(@PathVariable List<Long> ids) {
        return toExtendedList(employees.findAllById(ids));
    }

    @GetMapping("/search")
    public List<ExtendedEmployee> search(@RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(defaultValue = "0") int page,
            EmployeeCriteria criteria) {
        if (criteria == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return toExtendedList(searchEmployee.search(criteria,
                PageRequest.of(page, pageSize, Sort.Direction.ASC, Employee.Fields.id)));
    }

    private List<ExtendedEmployee> toExtendedList(Iterable<Employee> employees) {
        return StreamSupport.stream(employees.spliterator(), false).map(ExtendedEmployee::new).toList();
    }
}
