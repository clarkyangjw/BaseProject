package com.clark.service;

import com.clark.mapper.RoleMapper;
import com.clark.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public Role getRoleById(int id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public String addRole(Role role) {
        int id = 0;
        List<Role> roles = roleMapper.getRoles();
        for(Role r : roles){
            if(r.getName() == role.getName()){
                return "This Role Name is used, please enter again.";
            }
            if(r.getId() > id){
                id = r.getId();
            }
        }
        role.setId(++id);
        roleMapper.addRole(role);
        return "Role added successfully.";
    }

    @Override
    public String updateRole(Role role) {
        List<Role> roles = roleMapper.getRolesExceptByID(role.getId());
        for(Role r : roles){
            if(r.getName().equals(role.getName())){
                return "This Role Name is used, please enter again.";
            }
        }
        roleMapper.updateRole(role);
        return "Role updated successfully.";
    }

    @Override
    public int deleteRole(int id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleMapper.getRoleByName(name);
    }
}
