package com.xdozhao.iptv.common.m3u.util;

import com.xdozhao.iptv.common.m3u.entity.ExtInfEntity;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Slf4j
class M3uFileUtilTest {

    @Test
    void parseM3uFile() throws IOException {
        List<ExtInfEntity> extInfList = new LinkedList<>();
        String path = "/Users/zhaoxd/repository/git/iptv/m3u/ipv6.m3u";
        File file = new File(path);
        try (FileInputStream fis = FileUtils.openInputStream(file)) {
            extInfList = M3uFileUtil.parseM3uFile(fis, StandardCharsets.UTF_8);
        }
        log.info("Result:\n{}", extInfList);
    }

    @Test
    void parseM3uExtFile() throws IOException {
        List<ExtInfExtEntity> extInfList = new LinkedList<>();
        String path = "/Users/zhaoxd/repository/git/iptv/m3u/ipv6.m3u";
        File file = new File(path);
        try (FileInputStream fis = FileUtils.openInputStream(file)) {
            extInfList = M3uFileUtil.parseM3uExtFile(fis, StandardCharsets.UTF_8);
        }
        log.info("Result:\n{}", extInfList);
    }
}