package com.clark.config;

import com.clark.mapper.RoleAuthorityMapper;
import com.clark.pojo.RoleAuthority;
import com.clark.pojo.User;
import com.clark.service.AuthorityService;
import com.clark.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private AuthorityService authorityService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("perform Authorization doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.getRoleAuthorityByRoleId(currentUser.getRoleid());
        for(RoleAuthority ra : roleAuthorities){
            //System.out.println(authorityService.getAuthorityById(ra.getAuthorityid()).getName());
            info.addStringPermission(authorityService.getAuthorityById(ra.getAuthorityid()).getName());
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("perform Authentication doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByUserame(userToken.getUsername());
        if(user == null){
            return null;//UnknownAccountException
        }


        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
