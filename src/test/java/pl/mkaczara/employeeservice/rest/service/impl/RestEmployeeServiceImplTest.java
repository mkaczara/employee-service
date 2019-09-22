package pl.mkaczara.employeeservice.rest.service.impl;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.logic.aggregate.service.EmployeeAggregateService;
import pl.mkaczara.employeeservice.logic.crud.service.EmployeeCrudService;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.rest.exception.EmployeeNotFoundException;
import pl.mkaczara.employeeservice.rest.mapper.EmployeeRestDTOMapper;
import pl.mkaczara.employeeservice.rest.model.AggregateValue;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RestEmployeeServiceImplTest {

    private static final Long SAMPLE_EMPLOYEE_ID = 12L;

    @InjectMocks
    private RestEmployeeServiceImpl restEmployeeService;

    @Mock
    private EmployeeCrudService crudService;

    @Mock
    private EmployeeAggregateService aggregateService;

    @Mock
    private EmployeeRestDTOMapper restDTOMapper;

    private EmployeeRestDTO employeeRestDTO1;
    private EmployeeRestDTO employeeRestDTO2;
    private Employee employee1;
    private Employee employee2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employeeRestDTO1 = mock(EmployeeRestDTO.class);
        employeeRestDTO2 = mock(EmployeeRestDTO.class);
        employee1 = mock(Employee.class);
        employee2 = mock(Employee.class);

        when(restDTOMapper.map(employee1)).thenReturn(employeeRestDTO1);
        when(restDTOMapper.map(employee2)).thenReturn(employeeRestDTO2);
        when(restDTOMapper.map(employeeRestDTO1)).thenReturn(employee1);
        when(restDTOMapper.map(employeeRestDTO2)).thenReturn(employee2);
    }

    @Test
    public void shouldGetAll() throws Exception {
        Set<EmployeeRestDTO> expected = ImmutableSet.of(employeeRestDTO1, employeeRestDTO2);
        when(crudService.getAll()).thenReturn(ImmutableSet.of(employee1, employee2));

        Collection<EmployeeRestDTO> result = restEmployeeService.getAll();

        assertEquals(expected, result);
    }

    @Test
    public void shouldGetById() throws Exception {
        when(crudService.getById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.of(employee1));

        EmployeeRestDTO result = restEmployeeService.getById(SAMPLE_EMPLOYEE_ID);

        assertEquals(employeeRestDTO1, result);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void shouldThrowWhenGetByIdAndNoEmployeeFound() throws Exception {
        when(crudService.getById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.empty());

        restEmployeeService.getById(SAMPLE_EMPLOYEE_ID);
    }

    @Test
    public void shouldAdd() throws Exception {
        when(crudService.saveOrUpdate(employee1)).thenReturn(employee1);

        EmployeeRestDTO result = restEmployeeService.add(employeeRestDTO1);

        assertEquals(employeeRestDTO1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAddEmployeeWithId() throws Exception {
        when(employeeRestDTO1.getId()).thenReturn(SAMPLE_EMPLOYEE_ID);
        when(crudService.saveOrUpdate(employee1)).thenReturn(employee1);

        EmployeeRestDTO result = restEmployeeService.add(employeeRestDTO1);

        assertEquals(employeeRestDTO1, result);
    }

    @Test
    public void shouldUpdate() throws Exception {
        when(employeeRestDTO1.getId()).thenReturn(SAMPLE_EMPLOYEE_ID);
        when(crudService.saveOrUpdate(employee1)).thenReturn(employee1);

        EmployeeRestDTO result = restEmployeeService.update(employeeRestDTO1);

        assertEquals(employeeRestDTO1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenUpdateEmployeeWithoutId() throws Exception {
        when(crudService.saveOrUpdate(employee1)).thenReturn(employee1);

        EmployeeRestDTO result = restEmployeeService.update(employeeRestDTO1);

        assertEquals(employeeRestDTO1, result);
    }

    @Test
    public void shouldDeleteById() throws Exception {
        when(crudService.deleteById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.of(employee1));

        EmployeeRestDTO result = restEmployeeService.deleteById(SAMPLE_EMPLOYEE_ID);

        assertEquals(employeeRestDTO1, result);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void shouldThrowWhenDeleteByIdAndNoEmployeeFound() throws Exception {
        when(crudService.deleteById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.empty());

        restEmployeeService.deleteById(SAMPLE_EMPLOYEE_ID);
    }

    @Test
    public void shouldCalculateAverageAge() throws Exception {
        AggregateValue expected = new AggregateValue(49.5);
        when(aggregateService.calculateAverageAge()).thenReturn(49.5);

        AggregateValue result = restEmployeeService.calculateAverageAge();

        assertEquals(expected, result);
    }
}