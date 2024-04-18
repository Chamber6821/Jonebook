package com.example.jonebook.services.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.services.dto.EmployeeCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

class MemCachedSearchEmployeeTest {
  @Test
  void shouldReturnTheSameValueFromOrigin() {
    var pageable = PageRequest.of(0, 20);
    assertEquals(new MemCachedSearchEmployee((x, p) -> Page.<Employee>empty(p))
                     .search(EmployeeCriteria.builder().build(), pageable),
                 Page.<Employee>empty(pageable));
  }

  @Test
  void overflowCache() {
    var service =
        new MemCachedSearchEmployee((x, p) -> Page.<Employee>empty(p));
    for (int i = 0; i < 100000; i++) {
      var pageable = PageRequest.of(i, 20);
      assertEquals(service.search(EmployeeCriteria.builder().build(), pageable),
                   Page.<Employee>empty(pageable));
    }
  }
}
