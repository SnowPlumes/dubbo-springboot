package me.lv.facade.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import me.lv.core.user.biz.UserBiz;
import me.lv.facade.user.entity.User;
import me.lv.facade.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lzw
 * @date 2018/5/19
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserBiz userBiz;

    @Override
    public PageInfo<User> getUser(Integer page, Integer pageSize) {
        return userBiz.getUser(page, pageSize);
    }
}
