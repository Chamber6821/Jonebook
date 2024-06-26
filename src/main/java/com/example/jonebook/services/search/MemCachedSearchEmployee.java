package com.example.jonebook.services.search;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.services.dto.EmployeeCriteria;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class MemCachedSearchEmployee implements SearchEmployee {

  private final SearchEmployee origin;
  private final Map<Pair<EmployeeCriteria, Pageable>, Page<Employee>> cache =
      new HashMap<>();

  public MemCachedSearchEmployee(SearchEmployee origin) {
    this.origin = origin;
  }

  @Override
  public Page<Employee> search(@NonNull EmployeeCriteria criteria,
                               @NonNull Pageable pageable) {
    if (cache.size() > 10000)
      cache.keySet().stream().findFirst().ifPresent(cache::remove);
    var key = Pair.of(criteria, pageable);
    return cache.computeIfAbsent(key, x -> origin.search(criteria, pageable));
  }
}
