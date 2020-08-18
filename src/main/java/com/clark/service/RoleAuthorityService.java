package com.clark.service;

import com.clark.pojo.RoleAuthority;

import java.util.List;
import java.util.Map;

public interface RoleAuthorityService {

    List<RoleAuthority> getRoleAuthorities();

    Map<Integer, Integer> getRoleAuthorityByRoleId(int roleid);

    RoleAuthority getRoleAuthorityByRoleIDAndAuthorityID(int roleid, int authorityid);

    int addRoleAuthority(RoleAuthority roleAuthority);

    int updateRoleAuthority(String[] authorityBox);

    int deleteRoleAuthority(int roleid, int authorityid);


}
