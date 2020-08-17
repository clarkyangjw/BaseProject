package com.clark.service;

import com.clark.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee(Employee employee);

    int deleteEmployee(int id);

}
