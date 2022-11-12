package com.example.jonebook.entities;

import com.example.jonebook.entities.partial.EmployeeNoInternalPhone;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
public class Employee implements EmployeeNoInternalPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


