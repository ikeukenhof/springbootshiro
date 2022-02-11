package com.springbootdemoshiro.controller;

import com.springbootdemoshiro.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/rest")
public class RestAccountController {

    @GetMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            Account account = (Account) subject.getPrincipal();
            subject.getSession().setAttribute("account", account);
            return "登录成功！";
        } catch (UnknownAccountException e) {
            return "用户名错误！";
        } catch (IncorrectCredentialsException e) {
            return "密码错误！";
        }
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "please to login";
    }

    @GetMapping("/unAuthorization")
    public String unAuthorization() {
        return "you're unAuthorization";
    }

    @GetMapping("/main")
    public String main() {
        return "hello main";
    }

    @GetMapping("/manage")
    public String manage() {
        return "hello manage";
    }

    @GetMapping("/admin")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "您已成功退出登录！";
    }
}
