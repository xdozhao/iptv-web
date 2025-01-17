package com.xdozhao.iptv.common.m3u.util;

import cn.hutool.core.text.StrFormatter;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.common.m3u.entity.ExtInfEntity;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 15:19
 */
public class M3uFileUtil {

    static List<ExtInfEntity> parseM3uFile(InputStream is, Charset charset) {
        List<ExtInfEntity> extInfList = new LinkedList<>();
        List<String> lines = IOUtils.readLines(is, charset);
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).startsWith(M3u8Constant.EXTINF)) {
                continue;
            }
            String m3u = lines.get(i++) + "\n" + lines.get(i);
            extInfList.add(M3uUtil.parseM3u(m3u));
        }
        return extInfList;
    }

    public static List<ExtInfExtEntity> parseM3uExtFile(InputStream is, Charset charset) {
        List<ExtInfExtEntity> extInfList = new LinkedList<>();
        List<String> lines = IOUtils.readLines(is, charset);
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).startsWith(M3u8Constant.EXTINF)) {
                continue;
            }
            String m3u = lines.get(i++) + "\n" + lines.get(i);
            extInfList.add(M3uUtil.parseM3uExt(m3u));
        }
        return extInfList;
    }

    /**
     * 获取m3u文件时间签名
     *
     * @return 时间签名
     */
    public static String getTimestampSign() {
        // 添加时间戳
        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", "", "提示", "更新时间 " + dateTimeStr, "https://github.com/xdozhao/iptv-web");
    }

}
