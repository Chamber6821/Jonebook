package com.example.jonebook.entities.partial;

import com.example.jonebook.entities.Department;
import com.example.jonebook.entities.WorkPost;

import java.util.Set;

public interface EmployeeNoInternalPhone {
    Long getId();
    String getName();
    String getEmail();
    String getPhone();
    String getInternalPhone();
    Department getDepartment();
    Set<WorkPost> getPosts();
}
