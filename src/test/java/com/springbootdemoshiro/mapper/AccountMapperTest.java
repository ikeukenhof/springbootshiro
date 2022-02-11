package com.springbootdemoshiro.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springbootdemoshiro.entity.Account;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yx
 * @desc 账户
 * @date 2022-02-08
 */
@SpringBootTest
@MapperScan("com.springbootdemoshiro.mapper")
public class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void queryTest() {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "zs");
        Account account = accountMapper.selectOne(wrapper);
        System.out.println("account = " + account);
    }

    @Test
    void lambdaQueryTest() {
        Account account =
                accountMapper.selectOne(new LambdaQueryWrapper<Account>().eq(Account::getUsername, "ww"));
        System.out.println("account = " + account);
    }
}
