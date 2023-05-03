package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

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
@TableName("`leave`")
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
    private Date beginTime;

    /**
     * 请假结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 请假状态 2 未通过，1 通过，0 未审核
     */
    private Integer status;

    /**
     * 请假申请时间
     */
    private String createTime;

    /**
     * 请假审批时间
     */
    private String approveTime;

    /**
     * 请假人
     */
    @TableField("user_name")
    private String userName;

    /**
     * 审批人
     */
    @TableField("approve_name")
    private String approveName;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
