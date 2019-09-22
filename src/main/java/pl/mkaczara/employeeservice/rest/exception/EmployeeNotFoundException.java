package pl.mkaczara.employeeservice.rest.exception;

public class EmployeeNotFoundException extends Exception {

    private static final long serialVersionUID = 7549428956954150020L;

    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
