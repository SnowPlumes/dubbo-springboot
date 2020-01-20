package me.lv.common.constants;

/**
 * @author lzw
 * @date 2018/5/22
 */
public class CacheConstant {
    /**
     * 用户文件夹
     */
    protected static final String USER_FIRE = "Meitu-User:";

    /**
     * 用户基础缓存，hset，key：userId，value:userinfo的json对象
     */
    protected static final String USER_INFO = USER_FIRE + "UserInfo";

    /**
     * 用户token缓存，hset，key：token，value:userId字符串
     */
    protected static final String USER_TOKEN = USER_FIRE + "UserToken";
}
