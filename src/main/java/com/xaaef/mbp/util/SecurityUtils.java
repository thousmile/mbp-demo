package com.xaaef.mbp.util;

/**
 * All rights Reserved, Designed By 深圳市铭灏天智能照明设备有限公司
 * <p>
 * </p>
 *
 * @author Wang Chen Chen
 * @version 1.1
 * @date 2022/3/9 11:58
 * @copyright 2022 http://www.mhtled.com Inc. All rights reserved.
 */

public class SecurityUtils {

    private final static ThreadLocal<String> RPC_THREAD_LOCAL = new InheritableThreadLocal<>();

    /**
     * 获取 租户Id
     **/
    public static String getTenantId() {
        return RPC_THREAD_LOCAL.get();
    }


    /**
     * 设置 租户Id
     **/
    public static void setTenantId(String tenantId) {
        RPC_THREAD_LOCAL.set(tenantId);
    }


}
