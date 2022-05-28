package com.example.wsbootdemo.mycode.jdbc;

import com.example.wsbootdemo.mycode.jdbc.dao.MyDao;
import com.example.wsbootdemo.mycode.jdbc.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {

    private final MyDao dao;

    public JDBCCommandLineRunner(MyDao dao) {
        this.dao = dao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initial Number of records: " + dao.getCountOfEmployees());

        //--------------------------------------------------------------------------------
        dao.addEmplyee(10);
        //--------------------------------------------------------------------------------

        Employee emp = new Employee();
        emp.setId(11);
        emp.setAddress("Milano");
        emp.setFirstName("Wide");
        emp.setLastName("Solutions");
        dao.addEmplyeeUsingSimpelJdbcInsert(emp);

        //--------------------------------------------------------------------------------
        List<Employee> allEmployees = dao.getAllEmployees();
        for (Employee allEmployee : allEmployees) {
            System.out.println(allEmployee);
        }
        //--------------------------------------------------------------------------------



        System.out.println("Single element: "+dao.getEmployee(11));


        //--------------------------------------------------------------------------------
        dao.deleteEmplyeeUsingExecuteMethod(11);
        System.out.println("Current number of records: "+dao.getCountOfEmployees());
    }

}