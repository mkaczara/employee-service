package pl.mkaczara.employeeservice.rest.mapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.repository.entity.Gender;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeRestDTOMapperTest {

    @InjectMocks
    private EmployeeRestDTOMapper mapper;

    @Mock
    private GenderMapper genderMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldMapToEmployeeRestDTO() {
        Employee employeeToMap = new Employee(12L, "a", "b", 25, Gender.FEMALE);
        EmployeeRestDTO expected = new EmployeeRestDTO(12L, "a", "b", 25, 1);
        when(genderMapper.map(Gender.FEMALE)).thenReturn(1);

        EmployeeRestDTO result = mapper.map(employeeToMap);

        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToEmployee() {
        EmployeeRestDTO employeeToMap = new EmployeeRestDTO(12L, "a", "b", 25, 0);
        Employee expected = new Employee(12L, "a", "b", 25, Gender.FEMALE);
        when(genderMapper.map(1)).thenReturn(Gender.FEMALE);

        Employee result = mapper.map(employeeToMap);

        assertEquals(expected, result);
    }
}