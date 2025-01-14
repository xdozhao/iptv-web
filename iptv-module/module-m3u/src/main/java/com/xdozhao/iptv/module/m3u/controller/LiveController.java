package com.xdozhao.iptv.module.m3u.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrFormatter;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.common.m3u.util.M3uUtil;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.entity.convert.M3uExtInfConvert;
import com.xdozhao.iptv.module.m3u.entity.table.M3uExtInfTableDef;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * m3u直播源下载 控制层。
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/9 11:03
 */
@RestController
@AllArgsConstructor
@Tag(name = "m3u直播源下载")
@RequestMapping("/live")
public class LiveController {
    private IM3uExtInfService iM3uExtInfService;

    /**
     * 下载所有有效m3u资源
     *
     * @param request
     * @param response
     *
     * @return
     */
    @GetMapping("all")
    @Operation(description = "下载所有有效m3u资源")
    public ResponseEntity<String> all(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper wrapper = QueryWrapper.create();
        wrapper.where(M3uExtInfTableDef.M3U_EXT_INF.STATUS.eq("0"))
                .orderBy(M3uExtInfTableDef.M3U_EXT_INF.PRIORITY.asc());
        List<M3uExtInf> m3uExtInfList = iM3uExtInfService.list(wrapper);
        List<ExtInfExtEntity> extInfExtEntityList = M3uExtInfConvert.INSTANCE.toExtInfExtEntity(m3uExtInfList);
        StringBuilder result = new StringBuilder(M3u8Constant.EXTM3U).append("\n");
        for (ExtInfExtEntity extInfExt : extInfExtEntityList) {
            result.append(M3uUtil.extInfTag(extInfExt));
        }
        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", "", "提示", "更新时间 " + dateTimeStr, "https://github.com/xdozhao/iptv"));
        String filename = "all.m3u";
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename(FileNameUtil.getName(filename)).build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.valueOf(new Tika().detect(filename)))
                .body(result.toString());

    }
}
