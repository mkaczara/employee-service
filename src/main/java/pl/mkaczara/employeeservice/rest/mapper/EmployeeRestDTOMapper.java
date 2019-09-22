package pl.mkaczara.employeeservice.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

@Component
public class EmployeeRestDTOMapper {

    private GenderMapper genderMapper;
    private AddressRestDTOMapper addressRestDTOMapper;

    @Autowired
    public EmployeeRestDTOMapper(GenderMapper genderMapper, AddressRestDTOMapper addressRestDTOMapper) {
        this.genderMapper = genderMapper;
        this.addressRestDTOMapper = addressRestDTOMapper;
    }

    public EmployeeRestDTO map(Employee employee) {
        return new EmployeeRestDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                genderMapper.map(employee.getGender()),
                addressRestDTOMapper.map(employee.getAddresses())
        );
    }

    public Employee map(EmployeeRestDTO employee) {
        Employee mappedEmployee = new Employee(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                genderMapper.map(employee.getGender())
        );
        addressRestDTOMapper.map(employee.getAddresses()).forEach(mappedEmployee::addAddress);
        return mappedEmployee;
    }
}
