package com.clark.service;

import com.clark.mapper.AuthorityMapper;
import com.clark.pojo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public List<Authority> getAuthorities() {
        return authorityMapper.getAuthorities();
    }

    @Override
    public Authority getAuthorityById(int id) {
        return authorityMapper.getAuthorityById(id);
    }

    @Override
    public int addAuthority(Authority authority) {
        return authorityMapper.addAuthority(authority);
    }

    @Override
    public int updateAuthority(Authority authority) {
        return authorityMapper.updateAuthority(authority);
    }

    @Override
    public int deleteAuthority(int id) {
        return authorityMapper.deleteAuthority(id);
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityMapper.getAuthorityByName(name);
    }

    @Override
    public List<Authority> getAuthoritiesByUsername(String username) {
        return authorityMapper.getAuthoritiesByUsername(username);
    }
}
