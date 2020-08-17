package com.clark.mapper;

import com.clark.pojo.Department;
import com.clark.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> getDepartments();

    Department getDepartmentById(int id);

    int addDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(int id);

    Department getDepartmentByName(String name);

}
