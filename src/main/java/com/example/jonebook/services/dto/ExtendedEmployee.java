package com.example.jonebook.services.dto;

import com.example.jonebook.entities.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtendedEmployee extends PublicEmployee {

    private String internalPhone;

    public ExtendedEmployee(Employee employee) {
        super(employee);
        internalPhone = employee.getInternalPhone();
    }
}
