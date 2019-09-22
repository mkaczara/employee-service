package pl.mkaczara.employeeservice.logic.service.impl;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.entity.Employee;
import pl.mkaczara.employeeservice.repository.impl.EmployeeRepository;

import static org.mockito.Mockito.*;

public class EmployeeCrudServiceImplTest {

    private static final Long SAMPLE_EMPLOYEE_ID = 5L;

    @InjectMocks
    private EmployeeCrudServiceImpl employeeCrudService;

    @Mock
    private EmployeeRepository repository;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employee = mock(Employee.class);
    }

    @Test
    public void shouldDelegateGetAll() throws Exception {
        employeeCrudService.getAll();

        verify(repository).findAll();
    }

    @Test
    public void shouldDelegateGetById() throws Exception {
        employeeCrudService.getById(SAMPLE_EMPLOYEE_ID);

        verify(repository).findById(SAMPLE_EMPLOYEE_ID);
    }

    @Test
    public void shouldDelegateSaveOrUpdate() throws Exception {
        employeeCrudService.saveOrUpdate(employee);

        verify(repository).save(employee);
    }

    @Test
    public void shouldDelegateDeleteByIdIfExists() throws Exception {
        when(repository.findById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.of(employee));

        employeeCrudService.deleteById(SAMPLE_EMPLOYEE_ID);

        verify(repository).delete(employee);
    }

    @Test
    public void shouldDNotDelegateDeleteByIdIfNotExists() throws Exception {
        when(repository.findById(SAMPLE_EMPLOYEE_ID)).thenReturn(Optional.empty());

        employeeCrudService.deleteById(SAMPLE_EMPLOYEE_ID);

        verify(repository, never()).delete(any(Employee.class));
    }
}