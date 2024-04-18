package com.example.jonebook.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.example.jonebook.entities.Employee;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ExtendedEmployeeTest {
  @Test
  void createWithoutArgs() {
    var obj = new ExtendedEmployee();
    assertNotEquals(null, obj);
  }

  @Test
  void createFromEmployee() {
    var obj = new ExtendedEmployee(Employee.builder().posts(Set.of()).build());
    assertNotEquals(null, obj);
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
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmail());
    assertEquals(null, obj.getPhone());
    assertEquals(null, obj.getInternalPhone());
    assertEquals(null, obj.getDepartment());
    assertEquals(null, obj.getPosts());
  }

  @Test
  void getAll() {
    var obj = new ExtendedEmployee();
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmail());
    assertEquals(null, obj.getPhone());
    assertEquals(null, obj.getInternalPhone());
    assertEquals(null, obj.getDepartment());
    assertEquals(null, obj.getPosts());
  }
}
