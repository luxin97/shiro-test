package com.baizhi.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.DomainPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import java.util.ArrayList;

public class MyRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
//        拿着username去获取角色   根据角色查权限
//        身份    角色      权限

        System.out.println("-----------------++++------------------------------");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ArrayList<Permission> list=new ArrayList<Permission>();
        list.add(new DomainPermission("user:update"));
        list.add(new DomainPermission("user:add"));
        authorizationInfo.addObjectPermissions(list);
        return authorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken= (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
 //       User user = userDao.selectUserByUsername(username);
//        第一个参数：user.getUsername();
//        第二个参数：user.getPassword()，注意这里是指从数据库中获取的password。
//        第三个参数：realm，即当前realm的名称。也可以写成getName();
//        AuthenticationInfo authenticationInfo = new SimpleAccount(user,user.getPassword(),"MyRealm");

//        AuthenticationInfo authenticationInfo = new SimpleAccount("zhangsan","111111",getName());
        AuthenticationInfo authenticationInfo=null;
        if (username.equals("luxin")){
            authenticationInfo=new SimpleAccount("luxin","68609b8b64988c0f4def093eaa025e05",ByteSource.Util.bytes("abcd"),getName());
        }
        return authenticationInfo;
    }
}
