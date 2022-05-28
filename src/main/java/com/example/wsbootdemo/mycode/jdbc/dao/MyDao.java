package com.example.wsbootdemo.mycode.jdbc.dao;

import com.example.wsbootdemo.mycode.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MyDao {
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("EMPLOYEE");
    }

    public int getCountOfEmployees() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EMPLOYEE", Integer.class);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
    }

    public int addEmplyee(final int id) {
        return jdbcTemplate.update("INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)", id, "Bill", "Gates", "USA");
    }

    public int addEmplyeeUsingSimpelJdbcInsert(final Employee emp) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ID", emp.getId());
        parameters.put("FIRST_NAME", emp.getFirstName());
        parameters.put("LAST_NAME", emp.getLastName());
        parameters.put("ADDRESS", emp.getAddress());

        return simpleJdbcInsert.execute(parameters);
    }

    public Employee getEmployee(final int id) {
        final String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), id);
    }

    public void deleteEmplyeeUsingExecuteMethod(int id) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        namedParameterJdbcTemplate.update("DELETE FROM EMPLOYEE WHERE ID = :id", namedParameters);
    }



}
