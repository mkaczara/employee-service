package pl.mkaczara.employeeservice.logic.aggregate.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;
import pl.mkaczara.employeeservice.repository.impl.EmployeeRepository;

@Component
public class DatabaseAggregateCalculationStrategy implements AggregateCalculationStrategy {

    private EmployeeRepository employeeRepository;

    @Autowired
    public DatabaseAggregateCalculationStrategy(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AggregateCalculationMode getSupportedMode() {
        return AggregateCalculationMode.DATABASE;
    }

    @Override
    public Double calculateAverageAge() {
        return employeeRepository.getAvgAge();
    }
}
