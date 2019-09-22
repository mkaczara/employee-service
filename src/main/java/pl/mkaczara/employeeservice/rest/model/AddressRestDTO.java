package pl.mkaczara.employeeservice.rest.model;

import java.util.Objects;

public class AddressRestDTO {

    private Long id;
    private String streetAddress;
    private String city;
    private String postCode;
    private String state;

    public AddressRestDTO(Long id, String streetAddress, String city, String postCode, String state) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postCode = postCode;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressRestDTO that = (AddressRestDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(streetAddress, that.streetAddress) &&
                Objects.equals(city, that.city) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetAddress, city, postCode, state);
    }
}
