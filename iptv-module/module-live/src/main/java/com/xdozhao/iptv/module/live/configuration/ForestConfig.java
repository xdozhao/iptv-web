package com.xdozhao.iptv.module.live.configuration;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.dtflys.forest.converter.json.ForestFastjson2Converter;
import com.dtflys.forest.converter.json.ForestJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 14:45
 */
@Slf4j
@Configuration
public class ForestConfig {
    @Bean
    public ForestJsonConverter forestFastjson2Converter() {
        ForestFastjson2Converter converter = new ForestFastjson2Converter();
        // 设置日期格式
        converter.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置序列化特性
        converter.addWriterFeature(JSONWriter.Feature.IgnoreNoneSerializable);
        // 设置反序列化特性
        converter.addReadFeature(JSONReader.Feature.ErrorOnNoneSerializable);
        return converter;
    }

}
