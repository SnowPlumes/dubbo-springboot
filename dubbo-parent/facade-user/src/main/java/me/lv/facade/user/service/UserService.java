package me.lv.facade.user.service;

import com.github.pagehelper.PageInfo;
import me.lv.facade.user.entity.User;

/**
 * @author lzw
 * @date 2018/5/19
 */
public interface UserService {

    /**
     * 获取用户
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<User> getUser(Integer page, Integer pageSize);
}
