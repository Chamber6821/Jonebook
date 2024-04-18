package com.example.jonebook.services.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class CustomQuerySearchEmployeeTest {
  @Test
  void selectAll() {
    assertEquals(null,
                 new CustomQuerySearchEmployee(mock(EmployeeRepository.class))
                     .search(EmployeeCriteria.builder()
                                 .nameFragment("Petr")
                                 .emailFragment("@")
                                 .phonePrefix("123")
                                 .internalPhonePrefix("321")
                                 .departmentVariants(new HashSet<>() {
                                   { add("School"); }
                                 })
                                 .postsFragment(new HashSet<>() {
                                   { add("Teacher"); }
                                 })
                                 .build(),
                             null));
  }
}
