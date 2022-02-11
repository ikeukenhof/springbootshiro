package com.springbootdemoshiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class Account implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private String perms;

    /**
     *
     */
    private String role;

    private static final long serialVersionUID = 1L;
}

