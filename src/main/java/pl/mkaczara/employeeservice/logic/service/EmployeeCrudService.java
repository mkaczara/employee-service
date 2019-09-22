package pl.mkaczara.employeeservice.logic.service;

import java.util.Collection;
import java.util.Optional;
import pl.mkaczara.employeeservice.repository.entity.Employee;

public interface EmployeeCrudService {

    Collection<Employee> getAll();

    Optional<Employee> getById(Long id);

    Employee saveOrUpdate(Employee employee);

    Optional<Employee> deleteById(Long id);
}
