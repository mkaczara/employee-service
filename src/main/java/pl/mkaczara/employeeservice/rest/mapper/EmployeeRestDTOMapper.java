package pl.mkaczara.employeeservice.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

@Component
public class EmployeeRestDTOMapper {

    private GenderMapper genderMapper;

    @Autowired
    public EmployeeRestDTOMapper(GenderMapper genderMapper) {
        this.genderMapper = genderMapper;
    }

    public EmployeeRestDTO map(Employee employee) {
        return new EmployeeRestDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                genderMapper.map(employee.getGender())
        );
    }

    public Employee map(EmployeeRestDTO employee) {
        return new Employee(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                genderMapper.map(employee.getGender())
        );
    }
}
