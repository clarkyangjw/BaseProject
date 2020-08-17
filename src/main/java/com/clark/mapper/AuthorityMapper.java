package com.clark.mapper;

import com.clark.pojo.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityMapper {

    List<Authority> getAuthorities();

    Authority getAuthorityById(int id);

    int addAuthority(Authority authority);

    int updateAuthority(Authority authority);

    int deleteAuthority(int id);

    Authority getAuthorityByName(String name);

}
