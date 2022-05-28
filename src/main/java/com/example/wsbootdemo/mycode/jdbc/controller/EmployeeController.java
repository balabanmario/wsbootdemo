package com.example.wsbootdemo.mycode.jdbc.controller;

import com.example.wsbootdemo.mycode.jdbc.model.Employee;
import com.example.wsbootdemo.mycode.jdbc.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service=service;
    }

    @GetMapping(value = "/employees")
    public List<Employee> getAll() {
        return service.getAll();
    }
}
