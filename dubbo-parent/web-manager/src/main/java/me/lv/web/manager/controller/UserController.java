package me.lv.web.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import me.lv.common.dto.JsonResponse;
import me.lv.common.redis.service.UserCacheService;
import me.lv.facade.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 /**
 * @author lzw
 * @date 2018/5/19
 */
@Api(tags = "user")
@RestController
public class UserController {

    @Reference(timeout = 12000)
    private UserService userService;

    @Autowired
    private UserCacheService userCacheService;

    @ApiOperation(value = "获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "param"),
            @ApiImplicitParam(name = "pageSize", value = "页长", required = true, dataType = "Integer", paramType = "param")
    })
    @GetMapping("getUser")
    public JsonResponse getUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int pageSize) {
        userCacheService.addUserInfoCache("233", "zhangsang");
        userCacheService.addUserInfoCache("2333", "lishi");
        return JsonResponse.success(userService.getUser(page, pageSize));
    }
}
