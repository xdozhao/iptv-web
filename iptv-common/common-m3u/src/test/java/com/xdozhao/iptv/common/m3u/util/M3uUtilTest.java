package com.xdozhao.iptv.common.m3u.util;

import cn.hutool.core.util.IdUtil;
import com.xdozhao.iptv.common.m3u.entity.ExtInfEntity;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class M3uUtilTest {
    @Test
    void testInfTagBase() {
        String result = null;
        String duration = "10";
        String title = "CC";
        String url = "http://x/a.mp4";
        result = M3uUtil.extInfTag(duration, title, url);
        log.info("Result:\n{}", result);
        assertNotNull(result);
    }

    @Test
    void testInfTagExt() {
        String result = null;
        String duration = "10";
        String title = "CC";
        String url = "http://x/a.mp4";
        String tvgId = IdUtil.getSnowflakeNextIdStr();
        String tvgName="CD";
        String tvgLogo="http://x/a.png";
        String groupTitle="T";
        result = M3uUtil.extInfTag(duration, title, url,tvgId,tvgName,tvgLogo,groupTitle);
        log.info("Result:\n{}", result);
        result = M3uUtil.extInfTag("20", title, "http://x/b.mp4",IdUtil.getSnowflakeNextIdStr(),tvgName, "http://x/logo.mp4","TB");
        log.info("Result:\n{}", result);
        assertNotNull(result);
    }

    @Test
    void testInfObjTagBase() {
        String result = null;
        String duration = "10.13";
        String title = "CC";
        String url = "http://x/a.mp4";
        ExtInfEntity extInf = new ExtInfEntity();
        extInf.setDuration(new BigDecimal(duration));
        extInf.setTitle(title);
        extInf.setUrl(url);
        result = M3uUtil.extInfTag(extInf);
        log.info("Result:\n{}", result);
        assertNotNull(result);
    }


    @Test
    void testInfObjTagExt() {
        String result = null;
        String duration = "9.0";
        String title = "CC";
        String url = "http://x/a.mp4";
        String tvgId = IdUtil.getSnowflakeNextIdStr();
        String tvgName="CD";
        String tvgLogo="http://x/a.png";
        String groupTitle="T";
        ExtInfExtEntity extInf = new ExtInfExtEntity();
        extInf.setDuration(new BigDecimal(duration));
        extInf.setTitle(title);
        extInf.setUrl(url);
        extInf.setTvgId(tvgId);
        extInf.setTvgName(tvgName);
        extInf.setTvgLogo(tvgLogo);
        extInf.setGroupTitle(groupTitle);
        result = M3uUtil.extInfTag(extInf);
        log.info("Result:\n{}", result);
    }


    @Test
    void rsvM3u() {
        String m3u="#EXTINF:9.0 tvg-id=\"1876566239864111104\" tvg-name=\"CD\" tvg-logo=\"http://x/a.png\" group-title=\"T\",CC\nhttp://x/a.mp4";
        log.info("m3u:\n{}", m3u);
        ExtInfEntity extInfExt = M3uUtil.parseM3u(m3u);
        log.info("Result:\n{}", M3uUtil.extInfTag(extInfExt));
    }

    @Test
    void rsvM3uExt() {
        String m3u="#EXTINF:9.0 tvg-id=\"1876566239864111104\" tvg-name=\"CD\" tvg-logo=\"http://x/a.png\" group-title=\"T\",CC\nhttp://x/a.mp4";
        log.info("m3u:\n{}", m3u);
        ExtInfExtEntity extInfExt = M3uUtil.parseM3uExt(m3u);
        log.info("Result:\n{}", M3uUtil.extInfTag(extInfExt));
    }
}