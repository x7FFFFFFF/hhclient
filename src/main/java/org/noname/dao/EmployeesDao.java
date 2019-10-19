package org.noname.dao;

import org.noname.entities.Employee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeesDao {

    public List<Employee> getAllEmployees(){
        List<Employee> res = new ArrayList<>();
        Employee employee = new Employee();
        employee.setAge(20);
        employee.setSalary(new BigDecimal(50000));
        res.add(employee);
        return res;
    }
}
