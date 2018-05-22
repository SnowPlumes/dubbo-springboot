package com.meitu.common.redis.service;

/**
 * @author lzw
 */
public interface UserCacheService {

	/**
	 * 移除用户缓存
	 *
	 * @param userId
	 * @return
	 */
	Integer removeUserInfoCache(String userId);

    /**
     * 添加用户缓存
     *
     * @param userId
     * @param userInfo
     * @return
     */
    Integer addUserInfoCache(String userId, String userInfo);

    /**
     * 获取用户缓存
     * @param userId
     * @return
     */
    String getUserInfoCacheByUserId(String userId);
}
