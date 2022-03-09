package com.xaaef.mbp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "ml_area")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Area implements java.io.Serializable {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 区域名称
     *
     * @date 2021/7/2 15:58
     */
    private String areaName;

    /**
     * 租户 id
     *
     * @date 2021/7/2 15:58
     */
    private String tenantId;
}
