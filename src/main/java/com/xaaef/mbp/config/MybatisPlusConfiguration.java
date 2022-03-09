package com.xaaef.mbp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.xaaef.mbp.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * All rights Reserved, Designed By 深圳市铭灏天智能照明设备有限公司
 * <p>
 * </p>
 *
 * @author Wang Chen Chen
 * @version 1.0
 * @date 2021/7/8 9:21
 * @copyright 2021 http://www.mhtled.com Inc. All rights reserved.
 */


@Slf4j
@Configuration
@EnableTransactionManagement
@AllArgsConstructor
public class MybatisPlusConfiguration {

    private final TenantLineInnerInterceptor tenantLineInnerInterceptor;

    /**
     * 单页分页条数限制(默认无限制,参见 插件#handlerLimit 方法)
     */
    private static final Long MAX_LIMIT = 100L;

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        //分页插件: PaginationInnerInterceptor
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(MAX_LIMIT);

        //防止全表更新与删除插件: BlockAttackInnerInterceptor
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();

        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        return interceptor;
    }

    /**
     * 多租户配置中心
     */
    @Configuration
    @AllArgsConstructor
    @AutoConfigureBefore(MybatisPlusConfiguration.class)
    public static class TenantConfiguration {

        /**
         * 新多租户插件配置,一缓和二缓遵循mybatis的规则,
         * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
         * 避免缓存万一出现问题
         *
         * @return
         */
        @Bean
        public TenantLineInnerInterceptor tenantLineInnerInterceptor() {
            return new TenantLineInnerInterceptor(new TenantLineHandler() {

                @Override
                public Expression getTenantId() {
                    log.info("2. 进入 getTenantId : {}", SecurityUtils.getTenantId());
                    return new StringValue(SecurityUtils.getTenantId());
                }

                @Override
                public String getTenantIdColumn() {
                    return "tenant_id";
                }

                /**
                 * 过滤不需要根据租户隔离的表
                 * 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
                 */
                @Override
                public boolean ignoreTable(String tableName) {
                    // 如果 RPC 没有传递 token。就返回 true
                    boolean b = SecurityUtils.getTenantId() == null;
                    log.info("1. 进入 ignoreTable : {}", SecurityUtils.getTenantId());
                    return b;
                }

            });
        }

    }

}
