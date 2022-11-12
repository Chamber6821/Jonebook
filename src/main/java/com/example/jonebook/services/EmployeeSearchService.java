package com.example.jonebook.services;

import com.example.jonebook.entities.Department;
import com.example.jonebook.entities.Employee;
import com.example.jonebook.entities.WorkPost;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.EmployeeCriteria;
import com.example.jonebook.services.dto.ExtendedEmployee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeSearchService {

    private final EmployeeRepository repository;

    public EmployeeSearchService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<ExtendedEmployee> search(EmployeeCriteria criteria) {
        return repository.findAll(getSpecification(criteria)).stream()
                .map(ExtendedEmployee::new)
                .toList();
    }

    private Specification<Employee> getSpecification(EmployeeCriteria criteria) {
        return (root, query, criteriaBuilder) -> buildPredicate(root, criteriaBuilder, criteria);
    }

    private Predicate buildPredicate(Root<Employee> root, CriteriaBuilder builder, EmployeeCriteria criteria) {
        //TODO setup Hibernate Search with Elasticsearch (guid: https://reflectoring.io/hibernate-search/)

        ArrayList<Predicate> filters = new ArrayList<>();

        if (criteria.getNameFragment() != null)
            filters.add(builder.like(
                    builder.lower(root.get(Employee.Fields.name)),
                    contains(criteria.getNameFragment()).toLowerCase()));

        if (criteria.getPhonePrefix() != null)
            filters.add(builder.like(root.get(Employee.Fields.phone), startWith(criteria.getPhonePrefix())));

        if (criteria.getInternalPhonePrefix() != null)
            filters.add(builder.like(root.get(Employee.Fields.internalPhone), startWith(criteria.getInternalPhonePrefix())));

        if (criteria.getDepartmentVariants() != null)
            filters.add(belongTo(builder, root.get(Employee.Fields.department).get(Department.Fields.name), criteria.getDepartmentVariants()));

        if (criteria.getPostsFragment() != null)
            filters.add(containAll(builder, root.join(Employee.Fields.posts).get(WorkPost.Fields.name), criteria.getPostsFragment()));

        return builder.and(filters.toArray(Predicate[]::new));
    }

    private Predicate belongTo(CriteriaBuilder builder, Expression<String> field, Set<String> variants) {
        ArrayList<Predicate> variantPredicates = new ArrayList<>();

        for (String variant : variants) {
            variantPredicates.add(builder.equal(field, variant));
        }

        return builder.or(variantPredicates.toArray(Predicate[]::new));
    }

    private Predicate containAll(CriteriaBuilder builder, Expression<String> field, Set<String> set) {
        ArrayList<Predicate> variantPredicates = new ArrayList<>();

        for (String variant : set) {
            variantPredicates.add(builder.equal(field, variant));
        }

        return builder.and(variantPredicates.toArray(Predicate[]::new));
    }

    private String contains(String fragment) {
        return "%" + fragment + "%";
    }

    private String startWith(String prefix) {
        return prefix + "%";
    }
}
