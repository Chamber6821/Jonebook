package com.example.jonebook.services.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

class CriteriaSearchEmployeeTest {
  abstract class PartialEmployeeRepository implements EmployeeRepository {
    @Override
    public Page<Employee> findAll(Specification<Employee> spec,
                                  Pageable pageable) {
      var root = mock(Root.class);
      when(root.get(any(String.class))).thenReturn(mock(Path.class));
      when(root.join(any(String.class))).thenReturn(mock(Join.class));
      spec.toPredicate(root, mock(CriteriaQuery.class),
                       mock(CriteriaBuilder.class));
      return Page.<Employee>empty();
    }
  }

  @Test
  void searchAll() {
    var employees =
        mock(PartialEmployeeRepository.class, Answers.CALLS_REAL_METHODS);
    assertNotEquals(null, new CriteriaSearchEmployee(employees).search(
                              EmployeeCriteria.builder()
                                  .nameFragment("Petr")
                                  .emailFragment("@")
                                  .phonePrefix("123")
                                  .internalPhonePrefix("321")
                                  .departmentVariants(new HashSet<>() {
                                    { add("School"); }
                                  })
                                  .postsFragment(new HashSet<>() {
                                    { add("Teacher"); }
                                  })
                                  .build(),
                              null));
  }
}
