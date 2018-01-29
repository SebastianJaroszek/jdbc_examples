package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.EmployeesDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoMySqlTest {

    private static final Logger logger = Logger.getLogger(EmployeeDaoMySqlTest.class);

    @Test
    void shouldReturnAllEmployees(){
        EmployeesDao employeesDao = new EmployeesDao();

        List<Employee> result = null;
        try {
            result = employeesDao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        assertTrue(result.size() > 0);
    }

    @Test
    void shouldReturnEmployeeDataForEmployeeWithId(){
        EmployeesDao employeesDao = new EmployeesDao();

        Employee result = null;
        try {
            result = employeesDao.findById(100);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        assertEquals(result.getFirstName(), "Steven");
        assertEquals(result.getLastName(), "King");
        assertEquals(result.getEmployeeId(), 100);
    }

    @Test
    void shouldAddEmployeeToDbAndDelete(){
        Employee employee = new Employee();
        employee.setEmployeeId(502);
        employee.setFirstName("Sebastian");
        employee.setLastName("Jaroszek");
        employee.setEmail("sj2@emstories.pl");
        employee.setPhoneNumber("555.555.555");
        employee.setHireDate(LocalDate.of(2018, 05, 05));
        employee.setJobId("IT_PROG");
        employee.setSalary(100000.94);
        employee.setCommissionPct(0.3);
        employee.setManagerId(100);
        employee.setDepartmentId(60);

        EmployeesDao employeesDao = new EmployeesDao();
        Employee employeeFromDb = null;
        try {
            employeesDao.save(employee);
            employeeFromDb = employeesDao.findById(502);
            employeesDao.delete(502);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        employee.setHireDate(employee.getHireDate().minusDays(1));

        assertEquals(employeeFromDb, employee);
    }

    @Test
    public void shouldUpdateSalary(){
        EmployeesDao employeesDao = new EmployeesDao();

        Employee employeeBeforeUpdate = null;
        Employee employeeAfterUpdate = null;

        try {
            employeeBeforeUpdate = employeesDao.findById(100);
            employeesDao.updateSalary(100, 25000);

            employeeAfterUpdate = employeesDao.findById(100);

            employeesDao.updateSalary(100, 24000);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        assertTrue(employeeBeforeUpdate.getSalary() == 24000);
        assertTrue(employeeAfterUpdate.getSalary() == 25000);
    }

}
