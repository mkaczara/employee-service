package pl.mkaczara.employeeservice.logic.aggregate.strategy;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;
import pl.mkaczara.employeeservice.logic.crud.service.EmployeeCrudService;
import pl.mkaczara.employeeservice.repository.entity.Employee;

@Component
public class InMemoryAggregateCalculationStrategy implements AggregateCalculationStrategy {

    private EmployeeCrudService employeeCrudService;

    @Autowired
    public InMemoryAggregateCalculationStrategy(EmployeeCrudService employeeCrudService) {
        this.employeeCrudService = employeeCrudService;
    }

    @Override
    public AggregateCalculationMode getSupportedMode() {
        return AggregateCalculationMode.IN_MEMORY;
    }

    @Override
    public Double calculateAverageAge() {
        Collection<Employee> allEmployees = employeeCrudService.getAll();
        if (allEmployees.isEmpty()) {
            return 0.0d;
        }
        return allEmployees.stream()
                .mapToDouble(Employee::getAge)
                .average()
                .orElse(Double.NaN);
    }
}
