package com.example.jonebook.services.search;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomQuerySearchEmployee implements SearchEmployee {

    private EmployeeRepository employees;

    @Override
    public Page<Employee> search(@NonNull EmployeeCriteria criteria, @NonNull Pageable pageable) {
        return employees.findAllByMyCoolestInternationalQueryByAllDataInTheWord(
                Optional.ofNullable(criteria.getNameFragment()).orElse(""),
                Optional.ofNullable(criteria.getEmailFragment()).orElse(""),
                Optional.ofNullable(criteria.getPhonePrefix()).orElse(""),
                Optional.ofNullable(criteria.getInternalPhonePrefix()).orElse(""),
                criteria.getDepartmentVariants(),
                Optional.ofNullable(criteria.getPostsFragment()).orElse(Set.of()), pageable);
    }
}
