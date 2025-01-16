package com.xdozhao.iptv.module.m3u.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import com.xdozhao.iptv.module.m3u.mapper.IM3uExtInfMapper;
import com.xdozhao.iptv.module.m3u.service.IM3uExtInfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * m3u直播源表 服务层实现。
 *
 * @author zhaoxd
 * @since 18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class M3uExtInfServiceImpl extends ServiceImpl<IM3uExtInfMapper, M3uExtInf> implements IM3uExtInfService {

}
