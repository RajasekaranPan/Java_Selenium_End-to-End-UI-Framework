package database.repositories;

import database.DatabaseManager;
import database.QueryRepository;
import database.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeRepository {

    private EmployeeRepository() {
    }

    public static int getEmployeeCount() {

        String sql =
                QueryRepository.getQuery("employee.count");

        try (Connection connection =
                     DatabaseManager.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to retrieve employee count",
                    e
            );
        }
    }

    public static Employee getEmployeeByNumber(
            int employeeNumber) {

        String sql =
                QueryRepository.getQuery(
                        "employee.by.number");

        try (Connection connection =
                     DatabaseManager.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, employeeNumber);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapEmployee(resultSet);
                }

                return null;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to retrieve employee: "
                            + employeeNumber,
                    e
            );
        }
    }

    public static List<Employee> getAllEmployees() {

        String sql =
                QueryRepository.getQuery("employee.all");

        List<Employee> employees =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseManager.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapEmployee(resultSet));
            }

            return employees;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to retrieve employees",
                    e
            );
        }
    }

    private static Employee mapEmployee(
            ResultSet resultSet)
            throws SQLException {

        return new Employee(
                resultSet.getInt("emp_number"),
                resultSet.getString("emp_firstname"),
                resultSet.getString("emp_middle_name"),
                resultSet.getString("emp_lastname"),
                resultSet.getString("employee_id")
        );
    }
}