package com.xdozhao.iptv.module.m3u;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 15:16
 */
@Slf4j
@SpringBootApplication
public class ModuleM3uApp {
    public static void main(String[] args) {
        log.info("Begin to start the application......");
        ApplicationContext context = SpringApplication.run(ModuleM3uApp.class, args);
        log.info("......The application has been started!" + context.getApplicationName());
    }
}
