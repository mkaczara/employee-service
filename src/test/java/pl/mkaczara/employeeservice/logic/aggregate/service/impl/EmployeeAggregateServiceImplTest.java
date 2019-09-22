package pl.mkaczara.employeeservice.logic.aggregate.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.mkaczara.employeeservice.logic.aggregate.config.AggregateCalculationProperties;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;
import pl.mkaczara.employeeservice.logic.aggregate.strategy.AggregateCalculationStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeAggregateServiceImplTest {

    private static final Double AGGREGATE_VALUE = 5.0d;

    @InjectMocks
    private EmployeeAggregateServiceImpl aggregateService;

    @Mock
    private AggregateCalculationProperties aggregateCalculationProperties;

    @Spy
    private Collection<AggregateCalculationStrategy> strategies = new ArrayList<>();

    private AggregateCalculationStrategy strategy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        strategy = mock(AggregateCalculationStrategy.class);
        when(strategy.calculateAverageAge()).thenReturn(AGGREGATE_VALUE);
        when(strategy.getSupportedMode()).thenReturn(AggregateCalculationMode.DATABASE);
        strategies.add(strategy);

        when(aggregateCalculationProperties.getCalculationMode()).thenReturn(AggregateCalculationMode.DATABASE);
    }

    @Test
    public void shouldCalculateAverageAgeUsingProperStrategy() throws Exception {
        Double result = aggregateService.calculateAverageAge();

        assertEquals(AGGREGATE_VALUE, result, 0.0d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenNoStrategyFoundForMode() throws Exception {
        when(strategy.getSupportedMode()).thenReturn(AggregateCalculationMode.IN_MEMORY);

        aggregateService.calculateAverageAge();
    }
}