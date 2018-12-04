package com.wondertek.baiying.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 网上代码https://gitee.com/pengxiaokang/spring-boot-demo/tree/master/SpringBootDemo_JPA_Shiro
 * 身份校验核心类
 * Created by wd on 2017/10/11.
 */
public class MyShiroRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();

        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            System.out.println(role.getPermissions());
            for (SysPermission sysPermission : role.getPermissions()) {
                System.out.println(sysPermission);
                authorizationInfo.addStringPermission(sysPermission.getPermission());
            }

        }

        return authorizationInfo;
    }

    /**
     * 认证信息（身份验证） authentication 用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户的输入账号
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());

        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //UserInfo userInfo = userInfoService.findByUsername(username);
        UserInfo userInfo = new UserInfo();
        if (userInfo == null){
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                getName()); // realm name
        return authenticationInfo;
    }
}
