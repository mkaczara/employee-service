package pl.mkaczara.employeeservice.logic.aggregate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;

@Component
public class AggregateCalculationProperties {

    public static final String MODE_PROPERTY_NAME = "employee-service.aggregate.calculationMode";

    private Environment environment;

    @Autowired
    public AggregateCalculationProperties(Environment environment) {
        this.environment = environment;
    }

    public AggregateCalculationMode getCalculationMode() {
        String propertyValue = environment.getProperty(MODE_PROPERTY_NAME, AggregateCalculationMode.DATABASE.name());
        return AggregateCalculationMode.valueOf(propertyValue);
    }
}
