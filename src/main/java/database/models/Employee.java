package database.models;

public class Employee {

    private int employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;

    public Employee(
            int employeeNumber,
            String firstName,
            String middleName,
            String lastName,
            String employeeId) {

        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}