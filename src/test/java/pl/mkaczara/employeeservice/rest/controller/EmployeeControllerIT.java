package pl.mkaczara.employeeservice.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.mkaczara.employeeservice.rest.exception.EmployeeNotFoundException;
import pl.mkaczara.employeeservice.rest.model.AddressRestDTO;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIT {

    private static final String ENDPOINT_URL = "/api/v1/employee";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestEmployeeService employeeService;

    @Test
    public void shouldGetAll() throws Exception {
        EmployeeRestDTO employee1 = new EmployeeRestDTO(12L, "a", "b", 25, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        EmployeeRestDTO employee2 = new EmployeeRestDTO(13L, "a", "b", 30, 0, Collections.singletonList(
                new AddressRestDTO(2L, "street2", "city2", "postCode2", "state2")
        ));
        List<EmployeeRestDTO> employees = ImmutableList.of(employee1, employee2);
        when(employeeService.getAll()).thenReturn(employees);

        mvc.perform(get(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(employees.size())))
                .andExpect(content().string(toJson(employees)));
    }

    @Test
    public void shouldGetById() throws Exception {
        Long employeeId = 12L;
        EmployeeRestDTO employee = new EmployeeRestDTO(employeeId, "a", "b", 30, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        when(employeeService.getById(employeeId)).thenReturn(employee);

        mvc.perform(get(ENDPOINT_URL + "/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    @Test
    public void shouldReturn404WhenUnableToById() throws Exception {
        Long employeeId = 12L;
        when(employeeService.getById(employeeId)).thenThrow(EmployeeNotFoundException.class);

        mvc.perform(get(ENDPOINT_URL + "/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAdd() throws Exception {
        EmployeeRestDTO employeeToAdd = new EmployeeRestDTO(null, "a", "b", 25, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        EmployeeRestDTO addedEmployee = new EmployeeRestDTO(15L, "a", "b", 25, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        when(employeeService.add(employeeToAdd)).thenReturn(addedEmployee);

        mvc.perform(post(ENDPOINT_URL)
                .content(toJson(employeeToAdd))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(addedEmployee)));
    }

    @Test
    public void shouldUpdate() throws Exception {
        EmployeeRestDTO employee = new EmployeeRestDTO(5L, "a", "b", 30, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        when(employeeService.update(employee)).thenReturn(employee);

        mvc.perform(put(ENDPOINT_URL)
                .content(toJson(employee))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Long employeeId = 5L;
        EmployeeRestDTO employee = new EmployeeRestDTO(employeeId, "a", "b", 30, 0, Collections.singletonList(
                new AddressRestDTO(1L, "street1", "city1", "postCode1", "state1")
        ));
        when(employeeService.deleteById(employeeId)).thenReturn(employee);

        mvc.perform(delete(ENDPOINT_URL + "/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    @Test
    public void shouldReturn404WhenUnableToDeleteById() throws Exception {
        Long employeeId = 12L;
        when(employeeService.deleteById(employeeId)).thenThrow(EmployeeNotFoundException.class);

        mvc.perform(delete(ENDPOINT_URL + "/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    private String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}