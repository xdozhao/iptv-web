package com.xdozhao.iptv.module.m3u.init;

import cn.hutool.core.util.IdUtil;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uFileUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 10:35
 */
@Slf4j
@SpringBootTest
class M3uInitTest {
    @Autowired
    private IM3uExtInfService iM3uExtInfService;

    @Test
    void readIPV6() throws IOException {
        File file = new File("/Users/zhaoxd/repository/git/iptv/m3u/ipv6.m3u");
        List<ExtInfExtEntity> extInfExtList = null;
        try (FileInputStream fis = FileUtils.openInputStream(file)) {
            extInfExtList = M3uFileUtil.parseM3uExtFile(fis, StandardCharsets.UTF_8);
        }
        List<M3uExtInf> saveList = M3uExtInfConvert.INSTANCE.toM3uExtInfList(extInfExtList);
        for (M3uExtInf m3uExtInf : saveList) {
            m3uExtInf.setId(IdUtil.getSnowflakeNextIdStr());
            m3uExtInf.setStatus("0");
            m3uExtInf.setPlatform("fanmingming");
            m3uExtInf.setSource("https://live.fanmingming.cn/tv/m3u/ipv6.m3u");
            m3uExtInf.setPriority(0);
        }
        boolean result = iM3uExtInfService.saveBatch(saveList);
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }
}
