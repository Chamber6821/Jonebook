package com.example.jonebook.services.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class EmployeeCriteria {
    private String nameFragment;
    private String emailFragment;
    private String phonePrefix;
    private String internalPhonePrefix;
    private Set<String> departmentVariants;
    private Set<String> postsFragment;
}
