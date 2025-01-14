package com.xdozhao.iptv.common.core.constant;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 11:01
 */
public class M3u8Constant {
    /**
     * 文件头必须为 {@code #EXTM3U}
     */
    public static final String EXTM3U = "#EXTM3U";


    /**
     * 文件头必须为 {@code #EXTM3U}
     */
    public static final String EXTM3U_EXT_TEMPLATE = "#EXTM3U x-tvg-url=\"{}\"";

    /**
     * EXTM3U扩展属性
     * <p>
     * 用途：x-tvg-url通常用于指定一个URL，该URL指向一个包含电视指南信息的XML文件。这个XML文件通常包含节目的时间表、节目名称、描述等信息。
     * <p>
     * 应用场景：在一些IPTV或流媒体服务中，x-tvg-url用于帮助客户端获取当前播放节目的详细信息，从而在界面上显示节目指南或EPG（电子节目指南）。
     * <li>非标准属性：x-tvg-url并不是HLS标准的一部分，因此在不同的播放器和服务器实现中可能会有不同的支持程度。</li>
     * <li>兼容性：使用非标准属性时，需要确保播放器能够正确解析和处理这些属性，否则可能会导致播放问题或功能缺失.</li>
     */
    public static final String EXTM3U_ATTR_X_TVG_URL = "x-tvg-url";
    /**
     * 媒体文件信息标签
     */
    public static final String EXTINF = "#EXTINF";

    public static final String EXTINF_SEPARATOR= ",";

    /**
     * EXTINF扩展属性：指定频道的唯一标识符
     */
    public static final String EXTINF_ATTR_TVG_ID = "tvg-id";
    /**
     * EXTINF扩展属性：指定频道名称
     */
    public static final String EXTINF_ATTR_TVG_NAME = "tvg-name";
    /**
     * EXTINF扩展属性：指定频道logo的URL
     */
    public static final String EXTINF_ATTR_TVG_LOGO = "tvg-logo";
    /**
     * EXTINF扩展属性：指定频道所属的组
     */
    public static final String EXTINF_ATTR_GROUP_TITLE = "group-title";


    /**
     * 基本格式模板
     */
    public static final String EXTINF_BASE_TEMPLATE="#EXTINF:{},{}\n{}\n";

    /**
     * 基本格式模板
     */
    public static final String EXTINF_EXT_TEMPLATE="#EXTINF:{} tvg-id=\"{}\" tvg-name=\"{}\" tvg-logo=\"{}\" group-title=\"{}\",{}\n{}\n";
}
