package com.example.jonebook.repositories;

import com.example.jonebook.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    Optional<Department> findByName(String name);

    @Query("SELECT d FROM Department d WHERE name LIKE concat(:prefix, '%') ORDER BY name")
    List<Department> findByNameStartingWithOrderByName(@Param("prefix") String namePrefix);
}
