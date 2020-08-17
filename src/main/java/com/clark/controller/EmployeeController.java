package com.clark.controller;

import com.clark.mapper.EmployeeMapper;
import com.clark.pojo.*;
import com.clark.service.DepartmentService;
import com.clark.service.EmployeeService;
import com.clark.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;


    @GetMapping("/employee/getEmployees")
    public String getEmployees(Model model){
        List<Employee> employees = employeeService.getEmployees();
        List<Department> departments = departmentService.getDepartments();
        List<Position> positions = positionService.getPositions();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
//        for(User user : userList){
//            System.out.println(user);
//        }
        return "/employee/employee-list";
    }

    @GetMapping("/employee/employee")
    public String toAddEmployeePage(Model model){
        List<Department> departments = departmentService.getDepartments();
        List<Position> positions = positionService.getPositions();
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
//        System.out.println("=============");
        return "/employee/employee-add";
    }

    @PostMapping("/employee/employee")
    public String addUser(Employee employee, Model model){
//        System.out.println(employee);
        String msg = employeeService.addEmployee(employee);
        model.addAttribute("msg", msg);
        return "redirect:/employee/getEmployees";
    }

    @GetMapping("/employee/{id}")
    public String toEditEmployeePage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        List<Department> departments = departmentService.getDepartments();
        List<Position> positions = positionService.getPositions();
        //System.out.println(user);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
//        System.out.println(employee);
//        System.out.println(department);
//        System.out.println(position);
        return "/employee/employee-edit";
    }

    @PostMapping("/employee/updateEmployee")
    public String updateEmployee(Employee employee){
        System.out.println(employee);
        employeeService.updateEmployee(employee);
        Integer id = employee.getId();
        return "redirect:/employee/"+id.toString();
    }

    @GetMapping("/employee/deleteEmployee/{id}")
    public String deleteUser(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/employee/getEmployees";
    }

}
