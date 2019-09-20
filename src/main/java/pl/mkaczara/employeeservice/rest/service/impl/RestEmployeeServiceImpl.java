package pl.mkaczara.employeeservice.rest.service.impl;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.stereotype.Service;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

@Service
public class RestEmployeeServiceImpl implements RestEmployeeService {

    @Override
    public Collection<EmployeeRestDTO> getAll() {
        return Arrays.asList(
                new EmployeeRestDTO(1L, "A", "B", 25, 0),
                new EmployeeRestDTO(2L, "C", "D", 30, 0),
                new EmployeeRestDTO(3L, "", "", 35, 1),
                new EmployeeRestDTO(4L, "", "", 40, 1),
                new EmployeeRestDTO(5L, "", "", 45, 2),
                new EmployeeRestDTO(6L, "", "", 50, 1),
                new EmployeeRestDTO(7L, "", "", 55, 0),
                new EmployeeRestDTO(8L, "", "", 60, 0),
                new EmployeeRestDTO(9L, "", "", 65, 2),
                new EmployeeRestDTO(10L, "12", "", 70, 1)
        );
    }

    @Override
    public EmployeeRestDTO getById(Long id) {
        return new EmployeeRestDTO(id, "", "", 25, 0);
    }

    @Override
    public EmployeeRestDTO add(EmployeeRestDTO employee) {
        return employee;
    }

    @Override
    public EmployeeRestDTO update(EmployeeRestDTO employee) {
        return employee;
    }

    @Override
    public EmployeeRestDTO deleteById(Long id) {
        return new EmployeeRestDTO(id, "", "", 25, 1);
    }
}
