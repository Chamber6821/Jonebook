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
public interface EmployeeRepository
    extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
        @Query("""
                        SELECT e FROM Employee e
                            LEFT JOIN e.department d
                            WHERE lower(e.name) LIKE lower(concat('%', :nameFragment, '%'))
                            AND lower(e.email) LIKE lower(concat('%', :emailFragment, '%'))
                            AND e.phone LIKE lower(concat(:phonePrefix, '%'))
                            AND e.internalPhone LIKE lower(concat('%', :internalPhonePrefix, '%'))
                            AND (:departmentVariants IS NULL OR d.name IN (:departmentVariants))
                            AND NOT EXISTS (
                                 SELECT p.id FROM WorkPost p
                                 WHERE p.name IN (:postsFragment)
                                 AND p.id NOT IN (
                                     SELECT p2.id FROM Employee e2
                                     JOIN e2.posts p2 WHERE e2 = e))
                            GROUP BY e.id""")
        public Page<Employee> findAllByMyCoolestInternationalQueryByAllDataInTheWord(
                        String nameFragment,
                        String emailFragment,
                        String phonePrefix,
                        String internalPhonePrefix,
                        Set<String> departmentVariants,
                        Set<String> postsFragment,
                        Pageable pageable);
}
