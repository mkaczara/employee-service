package pl.mkaczara.employeeservice.rest.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkaczara.employeeservice.logic.aggregate.service.EmployeeAggregateService;
import pl.mkaczara.employeeservice.logic.crud.service.EmployeeCrudService;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.rest.exception.EmployeeNotFoundException;
import pl.mkaczara.employeeservice.rest.mapper.EmployeeRestDTOMapper;
import pl.mkaczara.employeeservice.rest.model.AggregateValue;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

@Service
public class RestEmployeeServiceImpl implements RestEmployeeService {

    private EmployeeCrudService employeeCrudService;
    private EmployeeAggregateService employeeAggregateService;
    private EmployeeRestDTOMapper restDTOMapper;

    @Autowired
    public RestEmployeeServiceImpl(EmployeeCrudService employeeCrudService, EmployeeAggregateService employeeAggregateService,
            EmployeeRestDTOMapper restDTOMapper) {
        this.employeeCrudService = employeeCrudService;
        this.employeeAggregateService = employeeAggregateService;
        this.restDTOMapper = restDTOMapper;
    }

    @Override
    public Collection<EmployeeRestDTO> getAll() {
        return employeeCrudService.getAll().stream().map(restDTOMapper::map).collect(Collectors.toSet());
    }

    @Override
    public EmployeeRestDTO getById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeCrudService.getById(id);
        return employee.map(restDTOMapper::map).orElseThrow((() -> new EmployeeNotFoundException("Unable to get employee with id: " + id)));
    }

    @Override
    public EmployeeRestDTO add(EmployeeRestDTO employee) {
        if (employee.getId() != null && employee.getId() > 0) {
            throw new IllegalArgumentException("Employee to add should not have an positive id");
        }
        Employee newEmployee = restDTOMapper.map(employee);
        newEmployee = employeeCrudService.saveOrUpdate(newEmployee);
        return restDTOMapper.map(newEmployee);
    }

    @Override
    public EmployeeRestDTO update(EmployeeRestDTO employee) {
        if (employee.getId() == null || employee.getId() == 0) {
            throw new IllegalArgumentException("Employee to update should have an positive id");
        }
        Employee changedEmployee = restDTOMapper.map(employee);
        changedEmployee = employeeCrudService.saveOrUpdate(changedEmployee);
        return restDTOMapper.map(changedEmployee);
    }

    @Override
    public EmployeeRestDTO deleteById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeCrudService.deleteById(id);
        return employee.map(restDTOMapper::map).orElseThrow(() -> new EmployeeNotFoundException("Unable to delete employee with id: " + id));
    }

    @Override
    public AggregateValue calculateAverageAge() {
        Double avgAge = employeeAggregateService.calculateAverageAge();
        return new AggregateValue(avgAge);
    }
}
