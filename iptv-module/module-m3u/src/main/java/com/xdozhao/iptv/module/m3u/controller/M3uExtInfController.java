package com.xdozhao.iptv.module.m3u.controller;

import com.mybatisflex.core.paginate.Page;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * m3u直播源表 控制层。
 *
 * @author zhaoxd
 * @since 8
 */
@RestController
@AllArgsConstructor
@Tag(name = "m3u直播源表接口")
@RequestMapping("/m3uExtInf")
public class M3uExtInfController {

    private IM3uExtInfService iM3uExtInfService;

    /**
     * 添加m3u直播源表。
     *
     * @param m3uExtInf m3u直播源表
     *
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description = "保存m3u直播源表")
    public boolean save(@RequestBody @Parameter(description = "m3u直播源表") M3uExtInf m3uExtInf) {
        return iM3uExtInfService.save(m3uExtInf);
    }

    /**
     * 根据主键删除m3u直播源表。
     *
     * @param id 主键
     *
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description = "根据主键m3u直播源表")
    public boolean remove(@PathVariable @Parameter(description = "m3u直播源表主键") String id) {
        return iM3uExtInfService.removeById(id);
    }

    /**
     * 根据主键更新m3u直播源表。
     *
     * @param m3uExtInf m3u直播源表
     *
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description = "根据主键更新m3u直播源表")
    public boolean update(@RequestBody @Parameter(description = "m3u直播源表主键") M3uExtInf m3uExtInf) {
        return iM3uExtInfService.updateById(m3uExtInf);
    }

    /**
     * 查询所有m3u直播源表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description = "查询所有m3u直播源表")
    public List<M3uExtInf> list() {
        return iM3uExtInfService.list();
    }

    /**
     * 根据m3u直播源表主键获取详细信息。
     *
     * @param id m3u直播源表主键
     *
     * @return m3u直播源表详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description = "根据主键获取m3u直播源表")
    public M3uExtInf getInfo(@PathVariable String id) {
        return iM3uExtInfService.getById(id);
    }

    /**
     * 分页查询m3u直播源表。
     *
     * @param page 分页对象
     *
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description = "分页查询m3u直播源表")
    public Page<M3uExtInf> page(@Parameter(description = "分页信息") Page<M3uExtInf> page) {
        return iM3uExtInfService.page(page);
    }
}
