package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao {

    private final static Logger logger = Logger.getLogger(EmployeesDao.class);

    public List<Employee> findAll() throws SQLException {

        String query = "SELECT * FROM employees";
        List<Employee> allEmployees = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date").toLocalDate());
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setCommissionPct(rs.getDouble("commission_pct"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setDepartmentId(rs.getInt("department_id"));

                allEmployees.add(employee);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }

        return allEmployees;
    }

    public Employee findById(Integer employeeId) throws SQLException {

        String query = "SELECT * FROM employees WHERE employee_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        Employee employee = null;

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);

            rs = statement.executeQuery();


            while (rs.next()) {
                employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date").toLocalDate());
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setCommissionPct(rs.getDouble("commission_pct"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setDepartmentId(rs.getInt("department_id"));

            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }

        return employee;
    }

}
