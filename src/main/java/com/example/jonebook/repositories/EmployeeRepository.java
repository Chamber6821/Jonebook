package com.example.jonebook.repositories;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.entities.partial.EmployeeNoInternalPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> getTop100ByOrderById();

    List<EmployeeNoInternalPhone> getPublicTop100ByOrderById();
}
