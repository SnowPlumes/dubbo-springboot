package com.meitu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author lzw
 * @date 2018/5/19
 */
@ServletComponentScan
@SpringBootApplication
public class ManagerApplication {

    private static Logger logger = LoggerFactory.getLogger(ManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
        logger.info(">>>>>>>>> manager start !!!");
    }
}
