package com.example.jonebook.services.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.services.dto.EmployeeCriteria;

public interface SearchEmployee {
    public Page<Employee> search(@NonNull EmployeeCriteria criteria, @NonNull Pageable pageable);
}
