package pl.mkaczara.employeeservice.rest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestEmployeeService employeeService;

    @Test
    public void shouldGetAll() throws Exception {
        EmployeeRestDTO employee1 = new EmployeeRestDTO(12L, "a", "b", 25, 0);
        EmployeeRestDTO employee2 = new EmployeeRestDTO(13L, "a", "b", 30, 0);
        List<EmployeeRestDTO> employees = Arrays.asList(employee1, employee2);
        when(employeeService.getAll()).thenReturn(employees);

        mvc.perform(get("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(employees.size())))
                .andExpect(content().string(toJson(employees)));
    }

    @Test
    public void shouldGetById() throws Exception {
        Long employeeId = 12L;
        EmployeeRestDTO employee = new EmployeeRestDTO(employeeId, "a", "b", 30, 0);
        when(employeeService.getById(employeeId)).thenReturn(employee);

        mvc.perform(get("/api/v1/employee/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    @Test
    public void shouldAdd() throws Exception {
        EmployeeRestDTO employeeToAdd = new EmployeeRestDTO(null, "a", "b", 25, 0);
        EmployeeRestDTO addedEmployee = new EmployeeRestDTO(15L, "a", "b", 25, 0);
        when(employeeService.add(employeeToAdd)).thenReturn(addedEmployee);

        mvc.perform(post("/api/v1/employee")
                .content(toJson(employeeToAdd))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(addedEmployee)));
    }

    @Test
    public void shouldUpdate() throws Exception {
        EmployeeRestDTO employee = new EmployeeRestDTO(5L, "a", "b", 30, 0);
        when(employeeService.update(employee)).thenReturn(employee);

        mvc.perform(put("/api/v1/employee")
                .content(toJson(employee))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Long employeeId = 5L;
        EmployeeRestDTO employee = new EmployeeRestDTO(employeeId, "a", "b", 30, 0);
        when(employeeService.deleteById(employeeId)).thenReturn(employee);

        mvc.perform(delete("/api/v1/employee/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(employee)));
    }

    private String toJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(object);
    }
}