package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
  @Test
  void getAll() {
    var obj = new Employee();
    assertEquals(obj.getId(), null);
    assertEquals(obj.getName(), null);
    assertEquals(obj.getEmail(), null);
    assertEquals(obj.getPhone(), null);
    assertEquals(obj.getInternalPhone(), null);
    assertEquals(obj.getDepartment(), null);
    assertEquals(obj.getPosts(), null);
  }

  @Test
  void setAll() {
    var obj = new Employee();
    obj.setId(null);
    obj.setName(null);
    obj.setEmail(null);
    obj.setPhone(null);
    obj.setInternalPhone(null);
    obj.setDepartment(null);
    obj.setPosts(null);
  }
}
