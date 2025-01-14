package com.xdozhao.iptv.common.m3u.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrFormatter;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.common.m3u.entity.ExtInfEntity;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 15:19
 */
public class M3uUtil {

    /**
     * 输出#EXTINF标签
     *
     * @param extInf #EXTINF标签
     *
     * @return #EXTINF标签
     */
    public static String extInfTag(ExtInfEntity extInf) {
        return extInfTag(extInf.getDuration().toPlainString(), extInf.getTitle(), extInf.getUrl());
    }

    /**
     * 输出#EXTINF 扩展标签
     *
     * @param extInfExt #EXTINF标签
     *
     * @return #EXTINF标签
     */
    public static String extInfTag(ExtInfExtEntity extInfExt) {
        return extInfTag(extInfExt.getDuration().toPlainString(), extInfExt.getTitle(), extInfExt.getUrl(), extInfExt.getTvgId(), extInfExt.getTvgName(), extInfExt.getTvgLogo(), extInfExt.getGroupTitle());
    }

    /**
     * 输出#EXTINF标签
     *
     * @param duration 持续时间(秒)
     * @param title    标题
     * @param url      媒体文件路径
     *
     * @return #EXTINF标签
     */
    public static String extInfTag(String duration, String title, String url) {
        // 空值处理
        duration = Convert.convert(String.class, duration, "-1");
        title = Convert.convert(String.class, title, "");
        url = Convert.convert(String.class, url, "");
        // 输出
        return StrFormatter.format(M3u8Constant.EXTINF_BASE_TEMPLATE, duration, title, url);
    }

    /**
     * 输出#EXTINF扩展标签
     *
     * @param duration   持续时间(秒)
     * @param title      标题
     * @param url        媒体文件路径
     * @param tvgId      频道的唯一标识符
     * @param tvgName    频道名称
     * @param tvgLogo    指定频道logo的URL
     * @param groupTitle 指定频道所属的组
     *
     * @return #EXTINF标签
     */
    public static String extInfTag(String duration, String title, String url, String tvgId, String tvgName, String tvgLogo, String groupTitle) {
        // 空值处理
        duration = Convert.convert(String.class, duration, "-1");
        title = Convert.convert(String.class, title, "");
        url = Convert.convert(String.class, url, "");
        tvgId = Convert.convert(String.class, tvgId, "");
        tvgName = Convert.convert(String.class, tvgName, "");
        tvgLogo = Convert.convert(String.class, tvgLogo, "");
        groupTitle = Convert.convert(String.class, groupTitle, "");
        // 输出
        return StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, duration, tvgId, tvgName, tvgLogo, groupTitle, title, url);
    }

    public static ExtInfEntity parseM3u(String m3u) {
        ExtInfEntity extInf = new ExtInfEntity();
        if (!m3u.startsWith(M3u8Constant.EXTINF)) {
            throw new IllegalArgumentException(m3u);
        }
        String[] rows = m3u.split("\n");
        if (rows.length != 2 || StringUtils.isBlank(rows[0]) || StringUtils.isBlank(rows[1])) {
            throw new IllegalArgumentException("Format m3u url error: " + m3u);
        }
        // row2
        extInf.setUrl(rows[1]);
        // row1
        rows = rows[0].split(",");
        if (rows.length != 2 || StringUtils.isBlank(rows[0]) || StringUtils.isBlank(rows[1])) {
            throw new IllegalArgumentException("Format m3u title error: " + m3u);
        }
        extInf.setTitle(rows[1]);
        rows[0] = rows[0].replace(M3u8Constant.EXTINF + ":", "");
        rows = rows[0].split(" ");
        extInf.setDuration(new BigDecimal(rows[0]));
        return extInf;
    }

    public static ExtInfExtEntity parseM3uExt(String m3u) {
        ExtInfExtEntity extInfExt = new ExtInfExtEntity();
        if (!m3u.startsWith(M3u8Constant.EXTINF)) {
            throw new IllegalArgumentException(m3u);
        }
        String[] rows = m3u.split("\n");
        if (rows.length != 2 || StringUtils.isBlank(rows[0]) || StringUtils.isBlank(rows[1])) {
            throw new IllegalArgumentException("Format m3u url error: " + m3u);
        }
        // row2
        extInfExt.setUrl(rows[1]);
        // row1
        rows = rows[0].split(",");
        if (rows.length != 2 || StringUtils.isBlank(rows[0]) || StringUtils.isBlank(rows[1])) {
            throw new IllegalArgumentException("Format m3u title error: " + m3u);
        }
        extInfExt.setTitle(rows[1]);
        rows[0] = rows[0].replace(M3u8Constant.EXTINF + ":", "");
        rows = rows[0].split(" ");
        extInfExt.setDuration(new BigDecimal(rows[0]));
        for (int i = 1; i < rows.length; i++) {
            if (rows[i].startsWith(M3u8Constant.EXTINF_ATTR_TVG_ID)) {
                extInfExt.setTvgId(rows[i].replace(M3u8Constant.EXTINF_ATTR_TVG_ID + "=", "").replace("\"", ""));
            }
            if (rows[i].startsWith(M3u8Constant.EXTINF_ATTR_TVG_NAME)) {
                extInfExt.setTvgName(rows[i].replace(M3u8Constant.EXTINF_ATTR_TVG_NAME + "=", "").replace("\"", ""));
            }
            if (rows[i].startsWith(M3u8Constant.EXTINF_ATTR_TVG_LOGO)) {
                extInfExt.setTvgLogo(rows[i].replace(M3u8Constant.EXTINF_ATTR_TVG_LOGO + "=", "").replace("\"", ""));
            }
            if (rows[i].startsWith(M3u8Constant.EXTINF_ATTR_GROUP_TITLE)) {
                extInfExt.setGroupTitle(rows[i].replace(M3u8Constant.EXTINF_ATTR_GROUP_TITLE + "=", "").replace("\"", ""));
            }
        }
        return extInfExt;
    }


    public static List<ExtInfEntity> parseM3u(InputStream is, Charset charset) {
        List<ExtInfEntity> extInfList = new LinkedList<>();
        List<String> lines = IOUtils.readLines(is, charset);
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).startsWith(M3u8Constant.EXTINF)) {
                continue;
            }
            String m3u = lines.get(i++) + lines.get(i);
            extInfList.add(parseM3u(m3u));
        }
        return extInfList;
    }


}
