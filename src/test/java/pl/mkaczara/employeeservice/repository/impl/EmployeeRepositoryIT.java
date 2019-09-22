package pl.mkaczara.employeeservice.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldGetAvgAge() throws Exception {
        Double result = employeeRepository.getAvgAge();

        assertEquals(47.5, result.doubleValue(), 0.0d);
    }
}