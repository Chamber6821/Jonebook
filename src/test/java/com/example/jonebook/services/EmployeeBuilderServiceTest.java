package com.example.jonebook.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.example.jonebook.repositories.DepartmentRepository;
import com.example.jonebook.repositories.WorkPostRepository;
import com.example.jonebook.services.dto.ExtendedEmployee;
import org.junit.jupiter.api.Test;

public class EmployeeBuilderServiceTest {
  @Test
  void x() {
    var obj = new EmployeeBuilderService(mock(DepartmentRepository.class),
        mock(WorkPostRepository.class));
    assertNotEquals(obj.createFromData(new ExtendedEmployee()), null);
  }
}
