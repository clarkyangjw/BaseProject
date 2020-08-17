package com.clark.service;

import com.clark.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRoleById(int id);

    String addRole(Role role);

    String updateRole(Role role);

    int deleteRole(int id);

    Role getRoleByName(String name);
}
