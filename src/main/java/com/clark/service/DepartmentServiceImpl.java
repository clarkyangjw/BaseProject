package com.clark.service;

import com.clark.mapper.DepartmentMapper;
import com.clark.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.addDepartment(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public int deleteDepartment(int id) {
        return departmentMapper.deleteDepartment(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentMapper.getDepartmentByName(name);
    }
}
