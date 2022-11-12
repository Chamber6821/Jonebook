package com.example.jonebook.services.dto;

import com.example.jonebook.entities.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendedEmployer extends PublicEmployee {

    private final String internalPhone;

    public ExtendedEmployer(Employee employee) {
        super(employee);
        internalPhone = employee.getInternalPhone();
    }
}
