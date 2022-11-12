package com.example.jonebook.services.dto;


import com.example.jonebook.entities.WorkPost;
import com.example.jonebook.entities.partial.EmployeeNoInternalPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public PublicEmployee(EmployeeNoInternalPhone employee) {
        id = employee.getId();
        name = employee.getName();
        email = employee.getEmail();
        phone = employee.getPhone();
        department = employee.getDepartment().getName();
        posts = employee.getPosts().stream().map(WorkPost::getName).toList();
    }
}
