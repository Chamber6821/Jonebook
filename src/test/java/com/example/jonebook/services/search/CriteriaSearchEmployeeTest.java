package com.example.jonebook.services.search;

import static org.mockito.Mockito.*;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class CriteriaSearchEmployeeTest {
  abstract class PartialEmployeeRepository implements EmployeeRepository {
    @Override
    public Page<Employee> findAll(Specification<Employee> spec,
        Pageable pageable) {
      spec.toPredicate(mock(Root.class), mock(CriteriaQuery.class),
          mock(CriteriaBuilder.class));
      return Page.<Employee>empty();
    }
  }

  @Test
  void searchAll() {
    var employees = mock(PartialEmployeeRepository.class);
    new CriteriaSearchEmployee(employees).search(
        EmployeeCriteria.builder()
            .nameFragment("Petr")
            .emailFragment("@")
            .phonePrefix("123")
            .internalPhonePrefix("321")
            .departmentVariants(new HashSet<>() {
              {
                add("School");
              }
            })
            .postsFragment(new HashSet<>() {
              {
                add("Teacher");
              }
            })
            .build(),
        null);
  }
}
