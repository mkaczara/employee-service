package pl.mkaczara.employeeservice.logic.aggregate.strategy;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.logic.crud.service.EmployeeCrudService;
import pl.mkaczara.employeeservice.repository.entity.Employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InMemoryAggregateCalculationStrategyTest {

    private static final Integer AGE_1 = 45;
    private static final Integer AGE_2 = 55;

    @InjectMocks
    private InMemoryAggregateCalculationStrategy inMemoryAggregateCalculationStrategy;

    @Mock
    private EmployeeCrudService employeeCrudService;

    private Employee employee1;
    private Employee employee2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employee1 = mock(Employee.class);
        when(employee1.getAge()).thenReturn(AGE_1);
        employee2 = mock(Employee.class);
        when(employee2.getAge()).thenReturn(AGE_2);
    }

    @Test
    public void shouldCalculateAverageAge() throws Exception {
        when(employeeCrudService.getAll()).thenReturn(ImmutableList.of(employee1, employee2));

        Double result = inMemoryAggregateCalculationStrategy.calculateAverageAge();

        assertEquals(50.0d, result, 0.0d);
    }

    @Test
    public void shouldGetZeroAverageWhenNoEmployees() throws Exception {
        List<Employee> employees = mock(List.class);
        when(employees.isEmpty()).thenReturn(Boolean.TRUE);
        when(employeeCrudService.getAll()).thenReturn(employees);

        Double result = inMemoryAggregateCalculationStrategy.calculateAverageAge();

        assertEquals(0.0d, result, 0.0d);
        verify(employees, never()).stream();
    }
}