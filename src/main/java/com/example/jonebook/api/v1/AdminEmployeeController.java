package com.example.jonebook.api.v1;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.DepartmentRepository;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.repositories.WorkPostRepository;
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
    private final DepartmentRepository departments;
    private final WorkPostRepository workPosts;
    private final EmployeeBuilderService builder;

    public AdminEmployeeController(EmployeeRepository employees, DepartmentRepository departments, WorkPostRepository workPosts, EmployeeBuilderService builder) {
        this.employees = employees;
        this.departments = departments;
        this.workPosts = workPosts;
        this.builder = builder;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ExtendedEmployee data) {
        return employees.save(builder.createFromData(data)).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ExtendedEmployee data) {
        Employee once = employees.findById(id).orElse(null);
        if (once == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (data.getName() != null) once.setName(data.getName());
        if (data.getEmail() != null) once.setEmail(data.getEmail());
        if (data.getPhone() != null) once.setPhone(data.getPhone());
        if (data.getInternalPhone() != null) once.setInternalPhone(data.getInternalPhone());

        if (data.getDepartment() != null) {
            departments.findByName(data.getDepartment()).ifPresent(once::setDepartment);
        }

        if (data.getPosts() != null) {
            for (String post : data.getPosts()) {
                workPosts.findByName(post).ifPresent(workPost -> once.getPosts().add(workPost));
            }
        }

        employees.save(once);
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
