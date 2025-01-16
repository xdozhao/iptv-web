package com.xdozhao.iptv.module.live.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.xdozhao.iptv.module.live.entity.LiveArea;
import com.xdozhao.iptv.module.live.mapper.ILiveAreaMapper;
import com.xdozhao.iptv.module.live.service.ILiveAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * bilibili直播分区 服务层实现。
 *
 * @author zhaoxd
 * @since 18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveAreaServiceImpl extends ServiceImpl<ILiveAreaMapper, LiveArea> implements ILiveAreaService {

}
