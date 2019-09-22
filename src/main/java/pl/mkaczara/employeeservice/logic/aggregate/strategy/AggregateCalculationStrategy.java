package pl.mkaczara.employeeservice.logic.aggregate.strategy;

import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;

public interface AggregateCalculationStrategy {

    Double calculateAverageAge();

    AggregateCalculationMode getSupportedMode();
}
