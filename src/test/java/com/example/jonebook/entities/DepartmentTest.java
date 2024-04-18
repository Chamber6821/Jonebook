package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DepartmentTest {
  @Test
  void getAll() {
    var obj = new Department();
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
  }

  @Test
  void setAll() {
    var obj = new Department();
    obj.setId(null);
    obj.setName(null);
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
  }
}
