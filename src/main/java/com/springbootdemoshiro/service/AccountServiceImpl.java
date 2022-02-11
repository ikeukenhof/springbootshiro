package com.springbootdemoshiro.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springbootdemoshiro.entity.Account;
import com.springbootdemoshiro.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yx
 * @desc
 * @date 2022-02-08
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        return accountMapper.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getUsername, username));
    }
}
