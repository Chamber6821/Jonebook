package com.example.jonebook.services.search;

import static org.mockito.Mockito.*;

import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CriteriaSearchEmployeeTest {
  @Test
  void searchAll() {
    new CriteriaSearchEmployee(mock(EmployeeRepository.class))
        .search(EmployeeCriteria.builder()
            .nameFragment("Petr")
            .emailFragment("@")
            .phonePrefix("123")
            .internalPhonePrefix("321")
            .departmentVariants(new HashSet<>() {
              {
                add("School");
              }
            })
            .postsFragment(new HashSet<>() {
              {
                add("Teacher");
              }
            })
            .build(),
            null);
  }
}
