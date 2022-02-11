package com.springbootdemoshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootdemoshiro.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yx
 * @desc 账户
 * @date 2022-02-08
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
