package com.clark.mapper;

import com.clark.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> getRoles();

    Role getRoleById(int id);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(int id);

    Role getRoleByName(String name);

    List<Role> getRolesExceptByID(int id);
}
