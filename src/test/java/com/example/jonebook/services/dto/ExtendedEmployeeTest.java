package com.example.jonebook.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.jonebook.entities.Employee;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ExtendedEmployeeTest {
  @Test
  void createWithoutArgs() {
    new ExtendedEmployee();
  }

  @Test
  void createFromEmployee() {
    new ExtendedEmployee(Employee.builder().posts(Set.of()).build());
  }

  @Test
  void setAll() {
    var obj = new ExtendedEmployee();
    obj.setId(null);
    obj.setName(null);
    obj.setEmail(null);
    obj.setPhone(null);
    obj.setInternalPhone(null);
    obj.setDepartment(null);
    obj.setPosts(null);
  }

  @Test
  void getAll() {
    var obj = new ExtendedEmployee();
    assertEquals(obj.getId(), null);
    assertEquals(obj.getName(), null);
    assertEquals(obj.getEmail(), null);
    assertEquals(obj.getPhone(), null);
    assertEquals(obj.getInternalPhone(), null);
    assertEquals(obj.getDepartment(), null);
    assertEquals(obj.getPosts(), null);
  }
}
