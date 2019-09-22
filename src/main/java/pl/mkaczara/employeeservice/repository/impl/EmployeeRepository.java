package pl.mkaczara.employeeservice.repository.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mkaczara.employeeservice.repository.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
