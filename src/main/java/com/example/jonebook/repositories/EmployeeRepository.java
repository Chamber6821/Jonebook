package com.example.jonebook.repositories;

import com.example.jonebook.entities.Employee;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query("SELECT e FROM Employee e\n"
            + "LEFT JOIN e.department d\n"
            + "WHERE lower(e.name) LIKE lower(concat('%', :nameFragment, '%'))\n"
            + "AND lower(e.email) LIKE lower(concat('%', :emailFragment, '%'))\n"
            + "AND e.phone LIKE lower(concat(:phonePrefix, '%'))\n"
            + "AND e.internalPhone LIKE lower(concat('%', :internalPhonePrefix, '%'))\n"
            + "AND (:departmentVariants IS NULL OR d.name IN (:departmentVariants))\n"
            + "AND NOT EXISTS (\n"
            + "     SELECT p.id FROM WorkPost p\n"
            + "     WHERE p.name IN (:postsFragment)\n"
            + "     AND p.id NOT IN (\n"
            + "         SELECT p2.id FROM Employee e2\n"
            + "         JOIN e2.posts p2 WHERE e2 = e))\n"
            + "GROUP BY e.id\n")
    public Page<Employee> findAllByMyCoolestInternationalQueryByAllDataInTheWord(
            String nameFragment,
            String emailFragment,
            String phonePrefix,
            String internalPhonePrefix,
            Set<String> departmentVariants,
            Set<String> postsFragment,
            Pageable pageable);
}
