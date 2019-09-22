package pl.mkaczara.employeeservice.logic.crud.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mkaczara.employeeservice.logic.crud.service.EmployeeCrudService;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.repository.impl.EmployeeRepository;

@Service
public class EmployeeCrudServiceImpl implements EmployeeCrudService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeCrudServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Collection<Employee> getAll() {
        Set<Employee> employees = new HashSet<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Optional<Employee> deleteById(Long id) {
        Optional<Employee> employee = getById(id);
        employee.ifPresent(emp -> employeeRepository.delete(emp));
        return employee;
    }
}
