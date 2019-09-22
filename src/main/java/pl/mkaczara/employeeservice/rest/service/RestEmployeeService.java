package pl.mkaczara.employeeservice.rest.service;

import java.util.Collection;
import pl.mkaczara.employeeservice.rest.exception.EmployeeNotFoundException;
import pl.mkaczara.employeeservice.rest.model.AggregateValue;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

public interface RestEmployeeService {

    Collection<EmployeeRestDTO> getAll();

    EmployeeRestDTO getById(Long id) throws EmployeeNotFoundException;

    EmployeeRestDTO add(EmployeeRestDTO employee);

    EmployeeRestDTO update(EmployeeRestDTO employee);

    EmployeeRestDTO deleteById(Long id) throws EmployeeNotFoundException;

    AggregateValue calculateAverageAge();
}
