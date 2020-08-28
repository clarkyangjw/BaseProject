package com.clark.controller;

import com.clark.pojo.*;
import com.clark.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private RoleService roleService;


    @GetMapping("user/getUsers")
    @RequiresPermissions("user:read")
    public String getUsers(Model model) {
        List<User> userList = userService.getUsers();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("users", userList);
        model.addAttribute("roles", roles);
        return "user/user-list";
    }

    @GetMapping("user/{id}")
    @RequiresPermissions("user:read")
    public String toEditUserPage(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        Role role = roleService.getRoleById(user.getRoleid());
        List<Role> roles = roleService.getRoles();
        Employee employee = employeeService.getEmployeeById(user.getEmployeeid());
        Department department = departmentService.getDepartmentById(employee.getDepartmentid());
        Position position = positionService.getPositionById(employee.getPositionid());
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        model.addAttribute("position", position);
        return "user/user-edit";
    }

    @PostMapping("user/updateUser")
    @RequiresPermissions("user:update")
    public String updateUser(User user, @RequestParam("file") MultipartFile file, Model model) {
        userService.updateUser(user);
        userService.uploadingAvatar(user, file);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("user/deleteUser/{id}")
    @RequiresPermissions("user:delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user/getUsers";
    }

}
