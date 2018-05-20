package com.meitu.core.user.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meitu.core.user.biz.UserBiz;
import com.meitu.core.user.dao.UserDao;
import com.meitu.facade.user.entity.User;
import com.meitu.facade.user.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzw
 * @date 2018/5/20
 */
@Component
public class UserBizImpl implements UserBiz{

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> getUser(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userDao.selectByExample(new UserExample());
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }
}
