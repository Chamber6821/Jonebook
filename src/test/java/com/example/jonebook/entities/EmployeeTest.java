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
}
