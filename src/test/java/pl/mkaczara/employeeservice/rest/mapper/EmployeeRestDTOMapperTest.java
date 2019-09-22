package pl.mkaczara.employeeservice.rest.mapper;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.entity.Address;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.repository.entity.Gender;
import pl.mkaczara.employeeservice.rest.model.AddressRestDTO;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeRestDTOMapperTest {

    @InjectMocks
    private EmployeeRestDTOMapper mapper;

    @Mock
    private GenderMapper genderMapper;

    @Mock
    private AddressRestDTOMapper addressMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldMapToEmployeeRestDTO() throws Exception {
        Address employeeToMapAddress = new Address(1L, "street", "city", "postcode", "state");
        Employee employeeToMap = new Employee(12L, "a", "b", 25, Gender.FEMALE);
        employeeToMap.addAddress(employeeToMapAddress);
        AddressRestDTO expectedAddress = new AddressRestDTO(1L, "street", "city", "postcode", "state");
        EmployeeRestDTO expected = new EmployeeRestDTO(12L, "a", "b", 25, 1, Collections.singletonList(expectedAddress));
        when(genderMapper.map(Gender.FEMALE)).thenReturn(1);
        when(addressMapper.map(Collections.singletonList(employeeToMapAddress))).thenReturn(Collections.singletonList(expectedAddress));

        EmployeeRestDTO result = mapper.map(employeeToMap);

        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToEmployee() throws Exception {
        AddressRestDTO employeeToMapAddress = new AddressRestDTO(1L, "street", "city", "postcode", "state");
        EmployeeRestDTO employeeToMap = new EmployeeRestDTO(12L, "a", "b", 25, 1, Collections.singletonList(employeeToMapAddress));
        Address expectedAddress = new Address(1L, "street", "city", "postcode", "state");
        Employee expected = new Employee(12L, "a", "b", 25, Gender.FEMALE);
        expected.addAddress(expectedAddress);
        when(genderMapper.map(1)).thenReturn(Gender.FEMALE);
        when(addressMapper.map(Collections.singletonList(employeeToMapAddress))).thenReturn(Collections.singletonList(expectedAddress));

        Employee result = mapper.map(employeeToMap);

        assertEquals(expected, result);
        assertThat(expected).isEqualToComparingFieldByField(result);
    }
}