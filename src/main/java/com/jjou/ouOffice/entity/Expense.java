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
 * 报销申请对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报销原因
     */
    private String reason;

    /**
     * 报销描述
     */
    private String description;

    /**
     * 报销金额
     */
    private Integer amount;

    /**
     * 报销申请时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 报销审批人
     */
    @TableField("approve_name")
    private String approveName;

    /**
     * 报销审批时间
     */
    @TableField("approve_time")
    private String approveTime;

    /**
     * 申请状态 2 未通过，1 通过，0 未审核
     */
    private Integer status;

    /**
     * 申请人
     */
    @TableField("user_name")
    private String userName;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
