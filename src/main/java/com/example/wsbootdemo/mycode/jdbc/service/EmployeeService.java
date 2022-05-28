package com.example.wsbootdemo.mycode.jdbc.service;

import com.example.wsbootdemo.mycode.jdbc.dao.MyDao;
import com.example.wsbootdemo.mycode.jdbc.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeeService(MyDao dao) {
        this.dao = dao;
    }

    private MyDao dao;

    public List<Employee> getAll() {
        return dao.getAllEmployees();
    }
}
