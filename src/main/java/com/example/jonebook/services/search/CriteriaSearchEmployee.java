package com.example.jonebook.services.search;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.jonebook.entities.Department;
import com.example.jonebook.entities.Employee;
import com.example.jonebook.entities.WorkPost;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CriteriaSearchEmployee implements SearchEmployee {

    private final EmployeeRepository employees;

    @Override
    public Page<Employee> search(@NonNull EmployeeCriteria criteria, @NonNull Pageable pageable) {
        return employees.findAll(specificationFrom(criteria), pageable);
    }

    private Specification<Employee> specificationFrom(EmployeeCriteria criteria) {
        return (root, query, criteriaBuilder) -> buildPredicate(root, criteriaBuilder, criteria);
    }

    private Predicate buildPredicate(Root<Employee> root, CriteriaBuilder builder, EmployeeCriteria criteria) {
        ArrayList<Predicate> filters = new ArrayList<>();

        if (criteria.getNameFragment() != null)
            filters.add(builder.like(
                    builder.lower(root.get(Employee.Fields.name)),
                    contains(criteria.getNameFragment()).toLowerCase()));

        if (criteria.getEmailFragment() != null)
            filters.add(builder.like(
                    builder.lower(root.get(Employee.Fields.email)),
                    contains(criteria.getEmailFragment()).toLowerCase()));

        if (criteria.getPhonePrefix() != null)
            filters.add(builder.like(root.get(Employee.Fields.phone), startsWith(criteria.getPhonePrefix())));

        if (criteria.getInternalPhonePrefix() != null)
            filters.add(builder.like(root.get(Employee.Fields.internalPhone),
                    startsWith(criteria.getInternalPhonePrefix())));

        if (criteria.getDepartmentVariants() != null)
            filters.add(belongTo(builder, root.get(Employee.Fields.department).get(Department.Fields.name),
                    criteria.getDepartmentVariants()));

        if (criteria.getPostsFragment() != null)
            filters.add(containAll(builder, root.join(Employee.Fields.posts).get(WorkPost.Fields.name),
                    criteria.getPostsFragment()));

        return builder.and(filters.toArray(Predicate[]::new));
    }

    private Predicate belongTo(CriteriaBuilder builder, Expression<String> field, Set<String> variants) {
        return builder.or(Stream.of(variants).map(v -> builder.equal(field, v)).toArray(Predicate[]::new));
    }

    private Predicate containAll(CriteriaBuilder builder, Expression<String> field, Set<String> set) {
        return builder.and(Stream.of(set).map(v -> builder.equal(field, v)).toArray(Predicate[]::new));
    }

    private String contains(String fragment) {
        return "%" + fragment + "%";
    }

    private String startsWith(String prefix) {
        return prefix + "%";
    }
}
