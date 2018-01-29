package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.EmployeesDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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

}
