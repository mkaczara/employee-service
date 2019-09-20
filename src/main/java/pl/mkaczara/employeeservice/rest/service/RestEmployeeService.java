package pl.mkaczara.employeeservice.rest.service;

import java.util.Collection;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

public interface RestEmployeeService {

    Collection<EmployeeRestDTO> getAll();

    EmployeeRestDTO getById(Long id);

    EmployeeRestDTO add(EmployeeRestDTO employee);

    EmployeeRestDTO update(EmployeeRestDTO employee);

    EmployeeRestDTO deleteById(Long id);
}
