package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentsDao {

    private final static Logger logger = Logger.getLogger(DepartmentsDao.class);

    public int countEmployeesByDepartmentId(int departmentId) throws SQLException {

        String query = "SELECT COUNT(*) AS liczba FROM employees WHERE department_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, departmentId);

            rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("liczba");
            }


        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }

        return 0;
    }

    public Employee findManagerByDepartmentId(int departmentId) throws SQLException {

        String query = "SELECT e.* FROM departments d JOIN employees e ON d.manager_id = e.employee_id WHERE d.department_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, departmentId);

            rs = statement.executeQuery();

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

                return employee;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
        return null;
    }

    public String findById(int departmentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String result = "";
        String query = "SELECT * FROM departments d JOIN locations l ON d.location_id = l.location_id JOIN countries co ON l.country_id = co.country_id JOIN regions r ON co.region_id = r.region_id WHERE d.department_id = ?";

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, departmentId);
            rs = statement.executeQuery();
            while (rs.next()) {
                result = result + String.valueOf(rs.getInt("d.department_id")) + " ";
                result = result + rs.getString("d.department_name") + " ";
                result = result + String.valueOf(rs.getInt("l.location_id")) + " ";
                result = result + rs.getString("l.street_address") + " ";
                result = result + rs.getString("co.country_name") + " ";
                result = result + rs.getString("r.region_name");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
        return result;
    }
}
