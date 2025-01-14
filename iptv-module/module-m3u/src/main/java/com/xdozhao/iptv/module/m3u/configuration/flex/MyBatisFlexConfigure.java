package com.xdozhao.iptv.module.m3u.configuration.flex;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 15:20
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.xdozhao.iptv.module.m3u.mapper"})
public class MyBatisFlexConfigure implements MyBatisFlexCustomizer {
    public MyBatisFlexConfigure() {
        //开启审计功能
        AuditManager.setAuditEnable(true);
        //
        AuditManager.setMessageFactory(new FlexMessageFactory());
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
            log.debug(">>>>>>Sql Audit: {}", auditMessage);
        });
    }

    /**
     * 自定义 MyBatis-Flex 配置。
     *
     * @param globalConfig 全局配置
     */
    @Override
    public void customize(FlexGlobalConfig globalConfig) {
    }
}
