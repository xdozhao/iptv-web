package com.xdozhao.iptv.module.generate;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.TableDefConfig;
import com.mybatisflex.codegen.dialect.IDialect;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 14:28
 */
@Slf4j
class TestM3uGenerate {
    @Test
    void testCodegen() {
        // 配置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.211.55.22:3306/iptv?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&connectTimeout=10000&socketTimeout=10000&autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

//        dataSource.setUrl("jdbc:postgresql://10.211.55.22:5432/postgres");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("123456");


        // 创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle();

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig, IDialect.DEFAULT);

        // 生成代码
        generator.generate();
    }

    private GlobalConfig createGlobalConfigUseStyle() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 包配置
        globalConfig.getPackageConfig()
                .setBasePackage("com.xdozhao.iptv.module.generate");

        // 策略配置
        globalConfig.getStrategyConfig()
                .setTablePrefix("b_")
                .setGenerateTable("b_room_info")
//                .setColumnConfig("tb_account", ColumnConfig.create().setColumnName("tenant_id").setLarge(true).setVersion(true)) // 可以单独配置某个列
                .setLogicDeleteColumn("del_flag")
                .setVersionColumn("version")
        ;

        // 注释配置
        globalConfig.getJavadocConfig()
                .setAuthor("zhaoxd")
                .setSince("18");

        // Entity 生成配置
        globalConfig.enableEntity()
                .setWithLombok(true)
                .setWithSwagger(true).setSwaggerVersion(EntityConfig.SwaggerVersion.DOC)
                .setWithActiveRecord(true)
                .setImplInterfaces(Serializable.class);

        // Mapper 生成配置
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.enableMapper()
                .setClassPrefix("I");

//        Service 生成配置
        globalConfig.enableService()
                .setClassPrefix("I")
                .setClassSuffix("Service")
                .setSuperClass(IService.class);
        // ServiceImpl 生成配置
        globalConfig.enableServiceImpl()
                .setClassPrefix("")
                .setClassSuffix("ServiceImpl")
                .setSuperClass(ServiceImpl.class);
        // Controller 生成配置
        globalConfig.enableController()
                .setClassSuffix("Controller")
                .setRestStyle(true)
//                .setSuperClass(BaseController.class)
        ;

        // TableDef 生成配置
        globalConfig.enableTableDef()
                .setClassPrefix("")
                .setClassSuffix("Def")
                .setPropertiesNameStyle(TableDefConfig.NameStyle.UPPER_CASE);

        // MapperXml 生成配置
        globalConfig.enableMapperXml()
                .setFilePrefix("I")
                .setFileSuffix("Mapper");

        return globalConfig;
    }
}
