package com.meitu;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author lzw
 * @date 2018/5/19
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.meitu.core.user.dao")
public class UserApplication {

    private static Logger logger = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        logger.info(">>>>>>>>> user-service start !!!");
    }
}
