package com.example.jonebook.services.dto;


import com.example.jonebook.entities.Department;
import com.example.jonebook.entities.Employee;
import com.example.jonebook.entities.WorkPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PublicEmployee {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private List<String> posts;

    public PublicEmployee(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        email = employee.getEmail();
        phone = employee.getPhone();
        department = Optional.ofNullable(employee.getDepartment()).map(Department::getName).orElse(null);
        posts = employee.getPosts().stream().map(WorkPost::getName).toList();
    }
}
