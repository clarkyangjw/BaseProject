package com.clark.mapper;

import com.clark.pojo.RoleAuthority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAuthorityMapper {

    List<RoleAuthority> getRoleAuthorities();

    List<RoleAuthority> getRoleAuthorityByRoleId(@Param("roleid")int roleid);

    RoleAuthority getRoleAuthorityByRoleIDAndAuthorityID(@Param("roleid")int roleid, @Param("authorityid")int authorityid);

    int addRoleAuthority(RoleAuthority roleAuthority);

    int updateRoleAuthority(RoleAuthority roleAuthority);

    int deleteRoleAuthority(@Param("roleid")int roleid, @Param("authorityid")int authorityid);

}
