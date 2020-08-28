package com.clark.service;

import com.clark.pojo.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAuthorities();

    Authority getAuthorityById(int id);

    int addAuthority(Authority authority);

    int updateAuthority(Authority authority);

    int deleteAuthority(int id);

    Authority getAuthorityByName(String name);

    List<Authority> getAuthoritiesByUsername(String username);

}
