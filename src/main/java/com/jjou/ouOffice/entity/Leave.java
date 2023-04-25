package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 请假申请对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 请假描述
     */
    private String description;

    /**
     * 请假开始时间
     */
    @TableField("begin_time")
    private LocalDateTime beginTime;

    /**
     * 请假结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 请假状态 1（true）已审核， 0（false）未审核
     */
    private Integer status;

    /**
     * 请假人
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
