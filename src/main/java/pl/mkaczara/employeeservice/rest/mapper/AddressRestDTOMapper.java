package pl.mkaczara.employeeservice.rest.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.repository.entity.Address;
import pl.mkaczara.employeeservice.rest.model.AddressRestDTO;

@Component
public class AddressRestDTOMapper {

    public Collection<AddressRestDTO> map(List<Address> addresses) {
        if (addresses == null || addresses.isEmpty()) {
            return new ArrayList<>();
        }
        return addresses.stream().map(this::map).collect(Collectors.toList());
    }

    public List<Address> map(Collection<AddressRestDTO> addresses) {
        if (addresses == null || addresses.isEmpty()) {
            return new ArrayList<>();
        }
        return addresses.stream().map(this::map).collect(Collectors.toList());
    }

    private AddressRestDTO map(Address address) {
        return new AddressRestDTO(
                address.getId(),
                address.getStreetAddress(),
                address.getCity(),
                address.getPostCode(),
                address.getState()
        );
    }

    private Address map(AddressRestDTO address) {
        return new Address(
                address.getId(),
                address.getStreetAddress(),
                address.getCity(),
                address.getPostCode(),
                address.getState()
        );
    }
}
