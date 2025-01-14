package com.xdozhao.iptv.module.m3u.init;

import cn.hutool.core.util.IdUtil;
import com.dtflys.forest.http.ForestResponse;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uFileUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.forest.openapi.YangOpenApi;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 15:31
 */
@Slf4j
@SpringBootTest
class YangInitTest {
    @Autowired
    private IM3uExtInfService iM3uExtInfService;
    @Autowired
    YangOpenApi openApi;

    @Test
    void gather() throws Exception {
        ForestResponse<String> rsp = openApi.gather();
        List<ExtInfExtEntity> extInfExtList = null;
        try (InputStream is = rsp.getInputStream()) {
            extInfExtList = M3uFileUtil.parseM3uExtFile(is, StandardCharsets.UTF_8);
        }
        List<M3uExtInf> saveList = new ArrayList<>();
        for (ExtInfExtEntity item : extInfExtList) {
            if ("https://epg.iill.top/v/301.mp4".equals(item.getUrl())
                    || "https://epg.iill.top/v/302.mp4".equals(item.getUrl())
                    || "•國際「AKTV」".equals(item.getGroupTitle())) {
                continue;
            }
            M3uExtInf save = M3uExtInfConvert.INSTANCE.toM3uExtInf(item);
            save.setId(IdUtil.getSnowflakeNextIdStr());
            save.setStatus("0");
            save.setPlatform("yang");
            save.setSource("https://tv.iill.top/m3u/Gather");
            save.setPriority(31);
            saveList.add(save);
        }
        boolean result = iM3uExtInfService.saveBatch(saveList);
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }

    @Test
    void live() throws Exception {
        ForestResponse<String> rsp = openApi.live();
        List<ExtInfExtEntity> extInfExtList = null;
        try (InputStream is = rsp.getInputStream()) {
            extInfExtList = M3uFileUtil.parseM3uExtFile(is, StandardCharsets.UTF_8);
        }
        List<M3uExtInf> saveList = new ArrayList<>();
        for (ExtInfExtEntity item : extInfExtList) {
            if ("https://epg.iill.top/v/301.mp4".equals(item.getUrl())
                    || "https://epg.iill.top/v/302.mp4".equals(item.getUrl())) {
                continue;
            }
            M3uExtInf save = M3uExtInfConvert.INSTANCE.toM3uExtInf(item);
            save.setId(IdUtil.getSnowflakeNextIdStr());
            save.setStatus("0");
            save.setPlatform("yang");
            save.setSource("https://tv.iill.top/m3u/live");
            save.setPriority(30);
            saveList.add(save);
        }
        boolean result = iM3uExtInfService.saveBatch(saveList);
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }
}
