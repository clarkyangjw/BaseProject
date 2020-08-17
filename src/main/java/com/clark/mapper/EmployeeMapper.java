package com.clark.mapper;

import com.clark.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int id);

    List<Employee> getEmployeesExceptByID(int id);
}
