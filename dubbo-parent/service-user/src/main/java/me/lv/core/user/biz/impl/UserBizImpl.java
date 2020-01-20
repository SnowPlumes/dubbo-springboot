package me.lv.core.user.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.lv.core.user.biz.UserBiz;
import me.lv.core.user.dao.UserDao;
import me.lv.facade.user.entity.User;
import me.lv.facade.user.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzw
 * @date 2018/5/20
 */
@Component
public class UserBizImpl implements UserBiz {

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
