package com.example.jonebook.services.search;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.services.dto.EmployeeCriteria;

@Service
public class MemCachedSearchEmployee implements SearchEmployee {

    private final SearchEmployee origin;
    private final Map<Pair<EmployeeCriteria, Pageable>, Page<Employee>> cache = new HashMap<>();

    public MemCachedSearchEmployee(SearchEmployee origin) {
        this.origin = origin;
    }

    @Override
    public Page<Employee> search(@NonNull EmployeeCriteria criteria, @NonNull Pageable pageable) {
        var key = Pair.of(criteria, pageable);
        if (!cache.containsKey(key))
            cache.put(key, origin.search(criteria, pageable));
        return cache.get(key);
    }
}
