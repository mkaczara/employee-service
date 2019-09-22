package pl.mkaczara.employeeservice.rest.model;

import java.util.Collection;
import java.util.Objects;

public class EmployeeRestDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer gender;
    private Collection<AddressRestDTO> addresses;

    public EmployeeRestDTO(Long id, String firstName, String lastName, Integer age, Integer gender, Collection<AddressRestDTO> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGender() {
        return gender;
    }

    public Collection<AddressRestDTO> getAddresses() {
        return addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeRestDTO that = (EmployeeRestDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(age, that.age) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, gender, addresses);
    }
}
