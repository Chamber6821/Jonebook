package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WorkPostTest {
  @Test
  void getAll() {
    var obj = new WorkPost();
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmployees());
  }

  @Test
  void setAll() {
    var obj = new WorkPost();
    obj.setId(null);
    obj.setName(null);
    obj.setEmployees(null);
    assertEquals(null, obj.getId());
    assertEquals(null, obj.getName());
    assertEquals(null, obj.getEmployees());
  }
}
