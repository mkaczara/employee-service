package pl.mkaczara.employeeservice.rest.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkaczara.employeeservice.rest.model.EmployeeRestDTO;
import pl.mkaczara.employeeservice.rest.service.RestEmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private RestEmployeeService employeeService;

    @Autowired
    public EmployeeController(RestEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<EmployeeRestDTO> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/employee/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EmployeeRestDTO get(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }

    @PostMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EmployeeRestDTO add(@RequestBody EmployeeRestDTO employee) {
        return employeeService.add(employee);
    }

    @PutMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EmployeeRestDTO update(@RequestBody EmployeeRestDTO employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping(path = "/employee/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EmployeeRestDTO delete(@PathVariable("id") Long id) {
        return employeeService.deleteById(id);
    }
}
