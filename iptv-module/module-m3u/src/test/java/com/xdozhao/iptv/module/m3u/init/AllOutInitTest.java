package com.xdozhao.iptv.module.m3u.init;

import cn.hutool.core.text.StrFormatter;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.entity.table.M3uExtInfTableDef;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 17:41
 */
@Slf4j
@SpringBootTest
class AllOutInitTest {
    @Autowired
    private IM3uExtInfService iM3uExtInfService;

    @Test
    void context() {
        QueryWrapper wrapper = QueryWrapper.create();
        wrapper.orderBy(M3uExtInfTableDef.M3U_EXT_INF.PRIORITY.asc());
        List<M3uExtInf> m3uExtInfList = iM3uExtInfService.list(wrapper);
        List<ExtInfExtEntity> extInfExtEntityList = M3uExtInfConvert.INSTANCE.toExtInfExtEntity(m3uExtInfList);
        StringBuffer result = new StringBuffer(M3u8Constant.EXTM3U).append("\n");
        for (ExtInfExtEntity extInfExt : extInfExtEntityList) {
            result.append(M3uUtil.extInfTag(extInfExt));
        }
        // 添加时间戳
        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", "", "提示", "更新时间 " + dateTimeStr, "https://github.com/xdozhao/iptv-web"));
        try {
            FileUtils.writeStringToFile(new File("db/all.m3u"), result.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("================================");
        log.info("Result: \n{}", result);
        log.info("================================");
    }
}
