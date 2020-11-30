package com.example.csy.shiro;


import com.example.csy.dao.UserMapper;
import com.example.csy.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * @MethodName doGetAuthorizationInfo 授权操作
     * @Description 权限配置类
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名信息
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 创建一个简单授权验证信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给这个用户设置从 role 表获取到的角色信息
        authorizationInfo.addRole(userMapper.queryUserByUsername(username).getRole().getRoleName());
        //给这个用户设置从 permission 表获取的权限信息
        authorizationInfo.addStringPermission(userMapper.queryPermissionByUsername(username).getPermissionName());
        return authorizationInfo;
    }

    /**
     * @MethodName doGetAuthenticationInfo 身份验证
     * @Description 认证配置类
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据在接受前台数据创建的 Token 获取用户名
        String username = (String) authenticationToken.getPrincipal();

//        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
//        System.out.println(userToken.getPrincipal());
//        System.out.println(userToken.getUsername());
//        System.out.println(userToken.getPassword());

        // 通过用户名查询相关的用户信息（实体）
        User user = userMapper.queryUserByUsername(username);
        if (user != null) {
            // 存入 Session，可选
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            // 密码认证的工作，Shiro 来做
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "userRealm");
            return authenticationInfo;
        } else {
            // 返回 null 即会抛异常
            return null;
        }
    }
}
