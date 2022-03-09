package com.xaaef.mbp;

import com.xaaef.mbp.mapper.AreaMapper;
import com.xaaef.mbp.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class MbpDemoApplicationTests {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 根据租户ID  获取当前租户全部数据
     *
     * @author Wang Chen Chen
     * @date 2022/3/9 12:26
     */
    @Test
    public void test1() {
        SecurityUtils.setTenantId("4b7aab");
        areaMapper.selectList(null).forEach(System.out::println);
    }


    /**
     * 根据 com.xaaef.mbp.config.MybatisPlusConfiguration.TenantConfiguration 中的配置
     *
     * 当租户ID为空 。 获取表中全部数据
     * 在 3.4.3.4 版本，会先进入 ignoreTable() 方法。然后再调用 getTenantId()
     * 在 3.5.1  版本，直接调用 getTenantId() 。 并没有 进入 ignoreTable() 方法
     *
     * @author Wang Chen Chen
     * @date 2022/3/9 12:26
     */
    @Test
    public void test2() {
        areaMapper.selectList(null).forEach(System.out::println);
    }


}
