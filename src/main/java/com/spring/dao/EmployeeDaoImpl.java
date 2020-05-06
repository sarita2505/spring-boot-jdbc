package com.spring.dao;

import com.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insertEmployee(Employee emp) {
		String sql = "INSERT INTO test_employee " +
				"(empId, empName) VALUES (?, ?)" ;
		//executes quary..internally it uses preparedstatement
		getJdbcTemplate().update(sql, new Object[]{
				emp.getEmpId(), emp.getEmpName()
		});
	}
	
	@Override
	public void insertEmployees(List<Employee> employees) {
		String sql = "INSERT INTO test_employee " + "(empId, empName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getEmpId());
				ps.setString(2, employee.getEmpName());
			}
			
			public int getBatchSize() {
				return employees.size();
			}
		});

	}
	@Override
	public List<Employee> getAllEmployees(){
		String sql = "SELECT * FROM test_employee";
		return getJdbcTemplate().query(sql, new EmployeeRowMapper());


	}

	@Override
	public Employee getEmployeeById(String empId) {
		String sql = "SELECT * FROM test_employee WHERE empId = ?";
		return getJdbcTemplate().queryForObject(sql,new Object[]{empId},new EmployeeRowMapper());
	}
}