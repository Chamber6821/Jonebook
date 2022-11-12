package com.example.jonebook.services;

import com.example.jonebook.entities.Employee;
import com.example.jonebook.repositories.EmployeeRepository;
import com.example.jonebook.services.dto.ExtendedEmployer;
import com.example.jonebook.services.dto.PublicEmployee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeBuilderService builder;

    public EmployeeService(EmployeeRepository repository, EmployeeBuilderService builder) {
        this.repository = repository;
        this.builder = builder;
    }

    public List<PublicEmployee> getAllPublic() {
        return repository.getPublicTop100ByOrderById().stream()
                .map(PublicEmployee::new)
                .toList();
    }

    public List<ExtendedEmployer> getAllExtended() {
        return repository.getTop100ByOrderById().stream()
                .map(ExtendedEmployer::new)
                .toList();
    }

    public List<ExtendedEmployer> getByIdsExtended(Iterable<Long> ids) {
        return repository.findAllById(ids).stream()
                .map(ExtendedEmployer::new)
                .toList();
    }

    public void removeByIds(Iterable<Long> ids) {
        repository.deleteAllById(ids);
    }

    public Long add(ExtendedEmployer data) {
        Employee entity = builder.createFromData(data);
        entity = repository.save(entity);
        return entity.getId();
    }
}
