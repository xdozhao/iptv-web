package com.xdozhao.iptv.module.live;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/10 17:57
 */
@Slf4j
@SpringBootApplication
public class LiveApplication {
    public static void main(String[] args) {
        log.info("Begin to start the application......");
        ApplicationContext context = SpringApplication.run(LiveApplication.class, args);
        log.info("......The application has been started!" + context.getApplicationName());
    }
}
