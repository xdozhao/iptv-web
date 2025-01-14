package com.xdozhao.iptv.module.m3u.configuration.flex;

import cn.hutool.core.net.NetUtil;
import com.mybatisflex.core.FlexConsts;
import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageFactory;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 15:20
 */
public class FlexMessageFactory implements MessageFactory {
    @Override
    public AuditMessage create() {
        // 在这里
        // 设置 message 的基础内容，包括 platform、module、url、user、userIp、hostIp 内容
        // 剩下的 query、queryParams、queryCount、queryTime、elapsedTime 为 mybatis-flex 设置

        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setPlatform(FlexConsts.NAME);
        auditMessage.setModule("module-m3u");
        auditMessage.setUser("user");
        auditMessage.setHostIp(NetUtil.getLocalhost().getHostAddress());
        return auditMessage;
    }
}
