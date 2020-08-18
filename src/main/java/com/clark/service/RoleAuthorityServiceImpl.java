package com.clark.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.clark.mapper.AuthorityMapper;
import com.clark.mapper.RoleAuthorityMapper;
import com.clark.pojo.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService{

    @Autowired
    RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    AuthorityMapper AuthorityMapper;

    @Override
    public List<RoleAuthority> getRoleAuthorities() {
        return roleAuthorityMapper.getRoleAuthorities();
    }

    @Override
    public Map<Integer, Integer> getRoleAuthorityByRoleId(int roleid) {
        List<RoleAuthority> roleAuthority = roleAuthorityMapper.getRoleAuthorityByRoleId(roleid);
        int sizeOfRoleAuthorityTable = AuthorityMapper.getAuthorities().size();
        Map<Integer, Integer> auth = new TreeMap<>();
        for (int i = 0; i < sizeOfRoleAuthorityTable; i++){
            auth.put(i,0);
        }
        for(RoleAuthority ra :roleAuthority){
            auth.replace(ra.getAuthorityid()-1,ra.getAuthorityid());
        }
        return auth;
    }

    @Override
    public RoleAuthority getRoleAuthorityByRoleIDAndAuthorityID(int roleid, int authorityid) {
        return roleAuthorityMapper.getRoleAuthorityByRoleIDAndAuthorityID(roleid, authorityid);
    }

    @Override
    public int addRoleAuthority(RoleAuthority roleAuthority) {
        return roleAuthorityMapper.addRoleAuthority(roleAuthority);
    }

    @Override
    public int updateRoleAuthority(String[] authorityBox) {
        int sizeOfRoleAuthorityTable = AuthorityMapper.getAuthorities().size();
        for(int i = 1; i < sizeOfRoleAuthorityTable + 1; i++){
            deleteRoleAuthority(Integer.parseInt(authorityBox[0]), i);
        }
        for(int i = 1; i < authorityBox.length; i++){
            roleAuthorityMapper.addRoleAuthority(new RoleAuthority(Integer.parseInt(authorityBox[0]), Integer.parseInt(authorityBox[i])));
        }
        return 0;
    }

    @Override
    public int deleteRoleAuthority(int roleid, int authorityid) {
        return roleAuthorityMapper.deleteRoleAuthority(roleid, authorityid);
    }
}
