package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeeTest {
  @Test
  void getAll() {
    var obj = new Employee();
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmail());
    assertEquals(null, obj.getPhone());
    assertEquals(null, obj.getInternalPhone());
    assertEquals(null, obj.getDepartment());
    assertEquals(null, obj.getPosts());
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
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmail());
    assertEquals(null, obj.getPhone());
    assertEquals(null, obj.getInternalPhone());
    assertEquals(null, obj.getDepartment());
    assertEquals(null, obj.getPosts());
  }
}
