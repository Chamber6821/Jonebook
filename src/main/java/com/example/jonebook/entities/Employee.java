package com.example.jonebook.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String internalPhone;

    @ManyToOne
    @JoinColumn
    private Department department;

    @ManyToMany
    @JoinTable
    private Set<WorkPost> posts;
}


