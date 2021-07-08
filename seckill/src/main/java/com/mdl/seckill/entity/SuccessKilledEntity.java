package com.mdl.seckill.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 秒杀成功明细
 * </p>
 *
 * @author xiekun
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("success_killed")
@ApiModel(value="SuccessKilledEntity对象", description="秒杀成功明细")
public class SuccessKilledEntity extends Model<SuccessKilledEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "秒杀商品id")
    @TableId("seckill_id")
    private Long seckillId;

    @ApiModelProperty(value = "用户手机号")
    @TableField("user_phone")
    private Long userPhone;

    @ApiModelProperty(value = "标识状态 -1 无效 0成功 1已付款 2 已发货 ")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.seckillId;
    }

}
