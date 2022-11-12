package com.example.jonebook.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@FieldNameConstants
@Entity
public class WorkPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "posts")
    private Set<Employee> employees;
}
