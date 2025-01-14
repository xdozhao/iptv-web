package com.xdozhao.iptv.module.m3u.init;

import cn.hutool.core.util.IdUtil;
import com.dtflys.forest.http.ForestResponse;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uFileUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.forest.openapi.AKTVOpenApi;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 15:52
 */
@Slf4j
@SpringBootTest
class AKTVInitTest {
    @Autowired
    private IM3uExtInfService iM3uExtInfService;
    @Autowired
    AKTVOpenApi aktvOpenApi;
    @Test
    void live() throws Exception {
        ForestResponse<String> rsp = aktvOpenApi.live();
        List<ExtInfExtEntity> extInfExtList = null;
        try (InputStream is = rsp.getInputStream()) {
            extInfExtList = M3uFileUtil.parseM3uExtFile(is, StandardCharsets.UTF_8);
        }
        List<M3uExtInf> saveList = M3uExtInfConvert.INSTANCE.toM3uExtInfList(extInfExtList);
        for (M3uExtInf m3uExtInf : saveList) {
            m3uExtInf.setId(IdUtil.getSnowflakeNextIdStr());
            m3uExtInf.setStatus("0");
            m3uExtInf.setPlatform("AKTV");
            m3uExtInf.setSource("https://aktv.top");
            m3uExtInf.setPriority(20);
        }
        boolean result = iM3uExtInfService.saveBatch(saveList);
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }
}
