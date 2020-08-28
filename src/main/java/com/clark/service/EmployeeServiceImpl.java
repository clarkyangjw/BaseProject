package com.clark.service;

import com.clark.mapper.EmployeeMapper;
import com.clark.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public String addEmployee(Employee employee) {
        List<Employee> employees = employeeMapper.getEmployees();
        for (Employee empt : employees) {
            if (empt.getEmail().equals(employee.getEmail())) {
                return "This Email is used, please enter again.";
            }
            if (empt.getPhone().equals(employee.getPhone())) {
                return "This Phone number is used, please enter again.";
            }
            if (empt.getSIN().equals(employee.getSIN())) {
                return "This SIN number is used, please enter again.";
            }
        }
        employeeMapper.addEmployee(employee);
        return "Employee added successfully.";
    }

    @Override
    public String updateEmployee(Employee employee) {
        List<Employee> employees = employeeMapper.getEmployeesExceptByID(employee.getId());
        for (Employee empt : employees) {
            if (empt.getEmail().equals(employee.getEmail())) {
                return "This Email is used, please enter again.";
            }
            if (empt.getPhone().equals(employee.getPhone())) {
                return "This Phone number is used, please enter again.";
            }
            if (empt.getSIN().equals(employee.getSIN())) {
                return "This SIN number is used, please enter again.";
            }
        }
        employeeMapper.updateEmployee(employee);
        return "Employee updated successfully.";
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeMapper.deleteEmployee(id);
    }

}
