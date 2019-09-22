package pl.mkaczara.employeeservice.logic.aggregate.service.impl;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mkaczara.employeeservice.logic.aggregate.config.AggregateCalculationProperties;
import pl.mkaczara.employeeservice.logic.aggregate.model.AggregateCalculationMode;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.repository.impl.EmployeeRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAggregateServiceImplIT {

    private static final Integer EMPLOYEE_AGE = 25;

    @Autowired
    private EmployeeAggregateServiceImpl employeeAggregateService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private AggregateCalculationProperties aggregateCalculationProperties;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employee = mock(Employee.class);
        when(employee.getAge()).thenReturn(EMPLOYEE_AGE);
    }

    @Test
    public void shouldCalculateAverageAgeUsingDatabase() throws Exception {
        when(aggregateCalculationProperties.getCalculationMode()).thenReturn(AggregateCalculationMode.DATABASE);
        when(employeeRepository.getAvgAge()).thenReturn(40.0d);

        Double result = employeeAggregateService.calculateAverageAge();

        assertEquals(40.0d, result, 0.0d);
    }

    @Test
    public void shouldCalculateAverageAgeInMemory() throws Exception {
        when(aggregateCalculationProperties.getCalculationMode()).thenReturn(AggregateCalculationMode.IN_MEMORY);
        when(employeeRepository.findAll()).thenReturn(ImmutableList.of(employee));

        Double result = employeeAggregateService.calculateAverageAge();

        assertEquals(25.0d, result, 0.0d);
    }
}