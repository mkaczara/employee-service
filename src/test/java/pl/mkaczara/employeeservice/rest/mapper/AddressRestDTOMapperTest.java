package pl.mkaczara.employeeservice.rest.mapper;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.entity.Address;
import pl.mkaczara.employeeservice.rest.model.AddressRestDTO;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

public class AddressRestDTOMapperTest {

    @InjectMocks
    private AddressRestDTOMapper mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldMapToAddressRestDTO() throws Exception {
        List<Address> addressesToMap = ImmutableList.of(
                new Address(1L, "street1", "city1", "postCode1", "state1"),
                new Address(2L, "street2", "city2", "postCode2", "state2")
        );
        List<AddressRestDTO> expected = ImmutableList.of(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1"),
                new AddressRestDTO(2L, "street2", "city2", "postCode2", "state2")
        );

        Collection<AddressRestDTO> result = mapper.map(addressesToMap);

        assertEquals(result, expected);
    }

    @Test
    public void shouldMapToEmptyAddressRestDTO() throws Exception {
        Collection<AddressRestDTO> result = mapper.map((List<Address>) null);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldMapToAddress() throws Exception {
        List<AddressRestDTO> addressesToMap = ImmutableList.of(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1"),
                new AddressRestDTO(2L, "street2", "city2", "postCode2", "state2")
        );
        List<Address> expected = ImmutableList.of(
                new Address(1L, "street1", "city1", "postCode1", "state1"),
                new Address(2L, "street2", "city2", "postCode2", "state2")
        );

        Collection<Address> result = mapper.map(addressesToMap);

        assertEquals(result, expected);
        assertThat(result).usingFieldByFieldElementComparator().containsExactlyElementsOf(expected);
    }

    @Test
    public void shouldMapToEmptyAddress() throws Exception {
        List<Address> result = mapper.map((Collection<AddressRestDTO>) null);

        assertThat(result).isEmpty();
    }
}