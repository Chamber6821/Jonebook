package com.example.jonebook.services.dto;

import com.example.jonebook.entities.Employee;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ExtendedEmployeeTest {
  @Test
  void createWithoutArgs() {
    new ExtendedEmployee();
  }

  @Test
  void createFromEmployee() {
    new ExtendedEmployee(Employee.builder().posts(Set.of()).build());
  }
}
