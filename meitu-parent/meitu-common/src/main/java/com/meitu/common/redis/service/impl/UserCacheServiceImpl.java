package com.meitu.common.redis.service.impl;

import com.meitu.common.constants.CacheConstant;
import com.meitu.common.redis.RedisManager;
import com.meitu.common.redis.service.UserCacheService;
import org.springframework.stereotype.Service;

/**
 * @author lzw
 * @date 2018/5/22
 */
@Service
public class UserCacheServiceImpl extends CacheConstant implements UserCacheService{

    @Override
    public Integer removeUserInfoCache(String userId) {
        String domain = this.getUserInfoCacheByUserId(userId);
        if (domain != null) {
            RedisManager.getInstance().hashDeleteHashKey(USER_INFO, userId);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer addUserInfoCache(String userId, String userInfo) {
        RedisManager.getInstance().HashSetValue(USER_INFO, userId, userInfo);
        return 1;
    }

    @Override
    public String getUserInfoCacheByUserId(String userId) {
        return RedisManager.getInstance().HashGetValue(USER_INFO, userId);
    }
}
