package com.springbootdemoshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yx
 * @desc shiro配置
 * @date 2022-02-08
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(
            @Qualifier("manager") DefaultWebSecurityManager manager) {
        /*
         * 这是 Shiro 自带的一个 Filter 工厂实例，
         * 所有的认证和授权判断都是由这个 bean 生成的 Filter 对象来完成的，
         * 这就是 Shiro 框架的运行机制，开发者只需要定义规则，进行配置，
         * 具体的执行者全部由 Shiro 自己创建的 Filter 来完成
         */
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        /*
         * 给 ShiroFilterFactoryBean 实例注入认证及授权规则。规则如下：
         *
         * 认证过滤器：
         * anon：无需认证即可访问，游客身份。
         * authc：必须认证（登录）才能访问。
         * authcBasic：需要通过 httpBasic 认证。
         * user：不一定已通过认证，只要是曾经被 Shiro 记住过登录状态的用户就可以正常发起请求，比如 rememberMe。
         *
         * 授权过滤器:
         * perms：必须拥有对某个资源的访问权限（授权）才能访问。
         * role：必须拥有某个角色权限才能访问。
         * port：请求的端口必须为指定值才可以访问。
         * rest：请求必须是 RESTful，method 为 post、get、delete、put。
         * ssl：必须是安全的 URL 请求，协议为 HTTPS。
         */
        Map<String, String> map = new HashMap<>();
        map.put("/main", "authc");
        map.put("/manage", "perms[manage]");
        map.put("/administrator", "roles[administrator]");
        map.put("/rest/main", "authc");
        map.put("/rest/manage", "perms[manage]");
        map.put("/rest/administrator", "roles[administrator]");
        factoryBean.setFilterChainDefinitionMap(map);
        // 设置登录页面【authentication】
        factoryBean.setLoginUrl("/rest/toLogin");
        // 未授权页面
        factoryBean.setUnauthorizedUrl("/rest/unAuthorization");
        return factoryBean;
    }

    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }

    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    /**
     * 配置shiro方言
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
