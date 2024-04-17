package com.example.jonebook.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WorkPostTest {
  @Test
  void getAll() {
    var obj = new WorkPost();
    assertEquals(obj.getId(), null);
    assertEquals(obj.getName(), null);
    assertEquals(obj.getEmployees(), null);
  }

  @Test
  void setAll() {
    var obj = new WorkPost();
    obj.setId(null);
    obj.setName(null);
    obj.setEmployees(null);
  }
}
