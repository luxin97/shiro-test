package com.baizhi.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.DomainPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import java.util.ArrayList;

public class TestShiro {
    public static void main(String[] args) {
        //安全管理工厂
        IniSecurityManagerFactory iniSecurityManagerFactory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.createInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject=SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken("luxin","123456");
        try{
            subject.login(token);
            System.out.println("认证成功");
        }catch(AuthenticationException e){
            System.out.println("认证失败");
            e.printStackTrace();
        }
        boolean b=subject.isAuthenticated();
        if (subject.isAuthenticated()){
//            基于角色
//            if (subject.hasRole("super")){
//                System.out.println("aaaa");
//            }

//            boolean[] roles = subject.hasRoles(Arrays.asList("super", "admin","sb"));
//            for (int i = 0; i < roles.length; i++) {
//                boolean role = roles[i];
//                System.out.println(role);
//            }

//            boolean roles = subject.hasAllRoles(Arrays.asList("super", "admin"));
//            System.out.println(roles);

//            基于资源
            ArrayList<Permission> list = new ArrayList<>();
            list.add(new DomainPermission("user:select"));
            list.add(new DomainPermission("user:add"));
            boolean[] booleans = subject.isPermitted(list);
            for (boolean aBoolean : booleans) {
                System.out.println("-------------"+aBoolean);
            }
        }
    }
}
