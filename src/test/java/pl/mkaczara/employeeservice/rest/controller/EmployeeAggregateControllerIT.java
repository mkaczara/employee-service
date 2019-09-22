package pl.mkaczara.employeeservice.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.mkaczara.employeeservice.rest.model.AggregateValue;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeAggregateController.class)
public class EmployeeAggregateControllerIT {

    private static final String ENDPOINT_URL = "/api/v1/employee/aggregate";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestEmployeeService employeeService;

    @Test
    public void shouldCalculateAverageAge() throws Exception {
        AggregateValue aggregateValue = new AggregateValue(49.5);
        when(employeeService.calculateAverageAge()).thenReturn(aggregateValue);

        mvc.perform(get(ENDPOINT_URL + "/avg/age")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(aggregateValue)));
    }
}