package pl.mkaczara.employeeservice.logic.aggregate.service.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkaczara.employeeservice.logic.aggregate.config.AggregateCalculationProperties;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;
import pl.mkaczara.employeeservice.logic.aggregate.service.EmployeeAggregateService;
import pl.mkaczara.employeeservice.logic.aggregate.strategy.AggregateCalculationStrategy;

@Service
public class EmployeeAggregateServiceImpl implements EmployeeAggregateService {

    private Collection<AggregateCalculationStrategy> strategies;
    private AggregateCalculationProperties aggregateCalculationProperties;

    @Autowired
    public EmployeeAggregateServiceImpl(Collection<AggregateCalculationStrategy> strategies, AggregateCalculationProperties aggregateCalculationProperties) {
        this.strategies = strategies;
        this.aggregateCalculationProperties = aggregateCalculationProperties;
    }

    @Override
    public Double calculateAverageAge() {
        AggregateCalculationMode mode = aggregateCalculationProperties.getCalculationMode();
        AggregateCalculationStrategy strategy = getStrategy(mode);
        return strategy.calculateAverageAge();
    }

    private AggregateCalculationStrategy getStrategy(AggregateCalculationMode mode) {
        return strategies.stream()
                .filter(strategy -> strategy.getSupportedMode() == mode)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find aggregate calculation strategy for mode: " + mode));
    }
}
