package com.spring.dao;

import com.spring.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpId(resultSet.getString("empId"));
        emp.setEmpName(resultSet.getString("empName"));
        return emp;
    }
}
