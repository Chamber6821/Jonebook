package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DepartmentTest {
  @Test
  void getAll() {
    var obj = new Department();
    assertEquals(obj.getId(), null);
    assertEquals(obj.getName(), null);
  }

  @Test
  void setAll() {
    var obj = new Department();
    obj.setId(null);
    obj.setName(null);
  }
}
