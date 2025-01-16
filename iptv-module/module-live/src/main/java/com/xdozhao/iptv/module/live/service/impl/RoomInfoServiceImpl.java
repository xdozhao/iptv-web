package com.xdozhao.iptv.module.live.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.mapper.IRoomInfoMapper;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * bilibili直播间信息 服务层实现。
 *
 * @author zhaoxd
 * @since 18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoomInfoServiceImpl extends ServiceImpl<IRoomInfoMapper, RoomInfo> implements IRoomInfoService {

}
