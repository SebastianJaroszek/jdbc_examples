package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.EmployeesDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import com.github.pabloo99.jdbc.dao.DepartmentsDao;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentsDaoMySqlTest {

    private static final Logger logger = Logger.getLogger(DepartmentsDaoMySqlTest.class);

    @Test
    void shouldReturnCountEmployeesForDepartment(){
        DepartmentsDao departmentsDao = new DepartmentsDao();
        int result = 0;
        try {
            result = departmentsDao.countEmployeesByDepartmentId(60);
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }

        assertEquals(result, 6);

    }

    @Test
    void shouldReturnDepartmentManager(){

        DepartmentsDao departmentsDao = new DepartmentsDao();
        Employee expected = null;

        Employee result = null;
        try {
            result = departmentsDao.findManagerByDepartmentId(10);
            expected = new EmployeesDao().findById(200);
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnLocationDataForDepartment(){
        DepartmentsDao departmentsDao = new DepartmentsDao();
        String result = "";
        try{
            result = departmentsDao.findById(60);
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
        assertEquals(result, "60 IT 1400 2014 Jabberwocky Rd United States of America Americas");
    }
}
