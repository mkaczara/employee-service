package pl.mkaczara.employeeservice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkaczara.employeeservice.rest.model.AggregateValue;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeAggregateController {

    private RestEmployeeService employeeService;

    @Autowired
    public EmployeeAggregateController(RestEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/aggregate/avg/age", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AggregateValue calculateAggregate() {
        return employeeService.calculateAverageAge();
    }
}
