package com.meitu.web.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.meitu.facade.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 /**
 * @author lzw
 * @date 2018/5/19
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("getUser")
    public String getUser(String name) {
        return userService.getUser(name);
    }
}
