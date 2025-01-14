package com.xdozhao.iptv.common.forest.interceptor;

import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.reflection.ForestMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 11:28
 */
@Slf4j
public class GlobalInterceptor<T> implements Interceptor<T> {
    /**
     * 默认回调函数: 接口方法执行时调用该方法
     * <p>默认为什么都不做
     *
     * @param request Forest请求对象
     * @param method  Forest方法对象
     * @param args    方法调用入参数组
     */
    @Override
    public void onInvokeMethod(ForestRequest request, ForestMethod method, Object[] args) {
        log.debug("onInvokeMethod");
        if (StringUtils.isBlank(request.getUserAgent())) {
            request.setUserAgent("Mozilla/5.0 (Windows NT; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        }
        log.debug("add header: User-Agent");
    }

    /**
     * 默认回调函数: 请求执行前调用该方法
     * <p>其返回值为布尔类型，可以控制请求是否继续执行
     * <p>默认为什么都不做
     *
     * @param request Forest请求对象
     *
     * @return {@code true}: 继续执行该请求, 否则中断请求
     */
    @Override
    public boolean beforeExecute(ForestRequest request) {
        log.debug("beforeExecute");
        return true;
    }


    /**
     * 默认回调函数: 在触发请求重试时执行
     * <p>默认为什么都不做
     *
     * @param request  Forest请求对象
     * @param response Forest响应对象
     */
    @Override
    public void onRetry(ForestRequest request, ForestResponse response) {
        log.warn("onRetry retry times: {}", request.getCurrentRetryCount());
    }

    /**
     * 默认回调函数: 请求成功后调用该方法
     * <p>默认为什么都不做
     *
     * @param data     请求响应返回后经过序列化后的数据
     * @param request  Forest请求对象
     * @param response Forest响应对象
     */
    @Override
    public void onSuccess(T data, ForestRequest request, ForestResponse response) {
        log.debug("onSuccess");
    }

    /**
     * 默认回调函数: 请求失败后调用该方法
     * <p>默认为什么都不做
     *
     * @param ex       请求失败的异常对象
     * @param request  Forest请求对象
     * @param response Forest响应对象
     */
    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
        log.error("onError");
    }

    /**
     * 默认回调函数: 请求完成后(成功/失败后) 调用该方法
     * <p>默认为什么都不做
     *
     * @param request  Forest请求对象
     * @param response Forest响应对象
     */
    @Override
    public void afterExecute(ForestRequest request, ForestResponse response) {
        log.debug("afterExecute");
    }}