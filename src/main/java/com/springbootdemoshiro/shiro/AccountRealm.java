package com.springbootdemoshiro.shiro;

import com.springbootdemoshiro.entity.Account;
import com.springbootdemoshiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yx
 * @desc 自定义过滤器
 * @date 2022-02-08
 */
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        // 设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        // 设置权限
        info.addStringPermission(account.getPerms());
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 获取用户信息
        Account account = accountService.findByUsername(token.getUsername());
        if (account == null) {
            return null;
        }

        // 返回用户认证信息
        return new SimpleAuthenticationInfo(
                // 用户名–此处传的是用户对象
                account,
                // 密码
                account.getPassword(),
                // 盐-！！字符串类型
                ByteSource.Util.bytes(account.getId().toString()),
                // 当前的realm名
                getName());
    }
}
