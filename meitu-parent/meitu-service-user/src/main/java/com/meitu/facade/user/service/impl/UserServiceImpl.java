package com.meitu.facade.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.meitu.facade.user.service.UserService;

/**
 * @author lzw
 * @date 2018/5/19
 */
@Service
public class UserServiceImpl implements UserService{

    @Override
    public String getUser(String name) {
        return "hello " + name;
    }
}
