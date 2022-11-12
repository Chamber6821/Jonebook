package com.example.jonebook.repositories;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.entities.partial.EmployeeNoInternalPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @NonNull
    Optional<Employee> findById(@NonNull Long id);

    List<Employee> getTop100ByOrderById();

    List<EmployeeNoInternalPhone> getPublicTop100ByOrderById();
}
