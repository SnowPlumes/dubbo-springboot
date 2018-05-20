package com.meitu.core.user.biz;

import com.github.pagehelper.PageInfo;
import com.meitu.facade.user.entity.User;

/**
 * @author lzw
 * @date 2018/5/20
 */
public interface UserBiz {

    /**
     * 获取用户
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<User> getUser(Integer page, Integer pageSize);
}
