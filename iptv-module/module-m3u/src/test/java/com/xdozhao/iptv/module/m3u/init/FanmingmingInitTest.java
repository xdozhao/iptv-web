package com.xdozhao.iptv.module.m3u.init;

import cn.hutool.core.util.IdUtil;
import com.dtflys.forest.http.ForestResponse;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uFileUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.forest.openapi.FanmingmingOpenApi;
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
 * @date 2025/1/8 10:35
 */
@Slf4j
@SpringBootTest
class FanmingmingInitTest {
    @Autowired
    FanmingmingOpenApi openApi;
    @Autowired
    private IM3uExtInfService iM3uExtInfService;

    @Test
    void ipv6() throws Exception {
        ForestResponse<String> rsp = openApi.ipv6();
        List<ExtInfExtEntity> extInfExtList = null;
        try (InputStream is = rsp.getInputStream()) {
            extInfExtList = M3uFileUtil.parseM3uExtFile(is, StandardCharsets.UTF_8);
        }
        List<M3uExtInf> saveList = M3uExtInfConvert.INSTANCE.toM3uExtInfList(extInfExtList);
        for (M3uExtInf m3uExtInf : saveList) {
            m3uExtInf.setId(IdUtil.getSnowflakeNextIdStr());
            m3uExtInf.setStatus("0");
            m3uExtInf.setPlatform("fanmingming");
            m3uExtInf.setSource("https://live.fanmingming.cn/tv/m3u/ipv6.m3u");
            m3uExtInf.setPriority(10);
        }
        boolean result = iM3uExtInfService.saveBatch(saveList);
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }
}
