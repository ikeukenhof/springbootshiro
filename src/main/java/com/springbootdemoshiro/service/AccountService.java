package com.springbootdemoshiro.service;

import com.springbootdemoshiro.entity.Account;

/**
 * @author yx
 * @desc 账户service
 * @date 2022-02-08
 */
public interface AccountService {
    Account findByUsername(String username);
}
