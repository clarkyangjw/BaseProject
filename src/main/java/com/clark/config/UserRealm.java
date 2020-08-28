package com.clark.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.clark.mapper.RoleAuthorityMapper;
import com.clark.pojo.Authority;
import com.clark.pojo.RoleAuthority;
import com.clark.pojo.User;
import com.clark.service.AuthorityService;
import com.clark.service.RoleService;
import com.clark.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("perform Authorization doGetAuthorizationInfo");
        String principal = principalCollection.getPrimaryPrincipal().toString();
        List<Authority> authorities = authorityService.getAuthoritiesByUsername(principal);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(!CollectionUtils.isEmpty(authorities)){
            info.addRole(roleService.getRoleById(userService.getUserByUserame(principal).getRoleid()).getName());
            authorities.forEach(authority -> {
                info.addStringPermission(authority.getName());
            });
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("perform Authentication doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByUserame(userToken.getUsername());
        System.out.println(user);
        if (user == null) {
            return null;//UnknownAccountException
        }


        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
    }
}
