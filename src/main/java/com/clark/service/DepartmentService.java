package com.clark.service;

import com.clark.pojo.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentById(int id);

    Department getDepartmentByName(String name);

    int addDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(int id);

}
