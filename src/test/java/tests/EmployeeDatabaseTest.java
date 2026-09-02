package tests;

import database.repositories.EmployeeRepository;
import tests.abstractClasses.DatabaseBaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeDatabaseTest extends DatabaseBaseTest {

    @Test
    public void verifyEmployeeCount() {

        int employeeCount =
                EmployeeRepository.getEmployeeCount();

        Assert.assertTrue(
                employeeCount > 0,
                "Employee count should be greater than zero"
        );
    }

    @Test
    public void verifyEmployeeExists() {

        var employee =
                EmployeeRepository.getEmployeeByNumber(1);

        Assert.assertNotNull(
                employee,
                "Employee should exist"
        );
    }

    @Test
    public void verifyAllEmployees() {

        var employees =
                EmployeeRepository.getAllEmployees();

        Assert.assertFalse(
                employees.isEmpty(),
                "Employee list should not be empty"
        );
    }
}