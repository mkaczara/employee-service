package pl.mkaczara.employeeservice.logic.aggregate.strategy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.impl.EmployeeRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DatabaseAggregateCalculationStrategyTest {

    private static final Double AGGREGATE_VALUE = 5.0d;

    @InjectMocks
    private DatabaseAggregateCalculationStrategy databaseAggregateCalculationStrategy;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCalculateAverageAgeUsingRepository() throws Exception {
        when(employeeRepository.getAvgAge()).thenReturn(AGGREGATE_VALUE);

        Double result = databaseAggregateCalculationStrategy.calculateAverageAge();

        assertEquals(AGGREGATE_VALUE, result, 0.0d);
    }
}