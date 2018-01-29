package com.github.pabloo99.jdbc;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import com.github.pabloo99.jdbc.dao.DepartmentsDao;

import java.sql.SQLException;

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
}
