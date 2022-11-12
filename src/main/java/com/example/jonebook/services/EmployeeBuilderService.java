package com.example.jonebook.services;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.DepartmentRepository;
import com.example.jonebook.repositories.WorkPostRepository;
import com.example.jonebook.services.dto.ExtendedEmployer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeBuilderService {

    private final DepartmentRepository departments;
    private final WorkPostRepository workPosts;

    public EmployeeBuilderService(DepartmentRepository departments, WorkPostRepository workPosts) {
        this.departments = departments;
        this.workPosts = workPosts;
    }

    public Employee createFromData(ExtendedEmployer data) {
        return Employee.builder()
                .name(data.getName())
                .phone(data.getPhone())
                .internalPhone(data.getInternalPhone())
                .email(data.getEmail())
                .department(departments.findByName(data.getDepartment()).orElse(null))
                .posts(Optional.ofNullable(data.getPosts()).stream()
                        .flatMap(Collection::stream)
                        .map(name -> workPosts.findByName(name).orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .build();
    }
}
