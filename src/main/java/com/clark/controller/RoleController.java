package com.clark.controller;

import com.clark.pojo.*;
import com.clark.service.AuthorityService;
import com.clark.service.RoleAuthorityService;
import com.clark.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleAuthorityService roleAuthorityService;


    @GetMapping("/role/getRoles")
    @RequiresPermissions("role:read")
    public String getRoles(Model model){

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "/roleAuthority/role-list";
    }

    @GetMapping("/role/role")
    @RequiresPermissions("role:create")
    public String toAddRolePage(){
        return "/roleAuthority/role-add";
    }

    @PostMapping("/role/role")
    @RequiresPermissions("role:create")
    public String addUser(Role role){
        roleService.addRole(role);
        return "redirect:/role/getRoles";
    }

    @GetMapping("/role/{id}")
    @RequiresPermissions("role:read")
    public String toEditRolePage(@PathVariable("id") Integer id, Model model,HttpSession session){
        Role role = roleService.getRoleById(id);
        Map<Integer, Integer> roleAuthorities = roleAuthorityService.getRoleAuthorityByRoleId(id);
        List<Authority> authorities = authorityService.getAuthorities();
        model.addAttribute("role", role);
        model.addAttribute("roleAuthorities", roleAuthorities);
        model.addAttribute("authorities", authorities);
        session.setAttribute("role", role);
        return "/roleAuthority/role-edit";
    }

    @PostMapping("/role/updateRole")
    @RequiresPermissions("role:update")
    public String updateRole(Role role){
        //System.out.println(role);
        roleService.updateRole(role);
        return "redirect:/role/"+role.getId().toString();
    }

    @PostMapping("/role/updateRoleAuthority")
    @RequiresPermissions("role:update")
    public String updateRoleAuthority(@RequestParam("authorityBox") String authorityBox[], HttpSession session){
        roleAuthorityService.updateRoleAuthority(authorityBox);
        Role role = (Role)session.getAttribute("role");
        return "redirect:/role/"+role.getId().toString();
    }

    @GetMapping("/role/deleteRole/{id}")
    @RequiresPermissions("role:delete")
    public String deleteRole(@PathVariable("id") int id){
        roleService.deleteRole(id);
        Map<Integer, Integer> roleAuthorities = roleAuthorityService.getRoleAuthorityByRoleId(id);
        for(int i = 0; i < roleAuthorities.size(); i++){
            roleAuthorityService.deleteRoleAuthority(id, roleAuthorities.get(i));
        }
        return "redirect:/role/getRoles";
    }

}
