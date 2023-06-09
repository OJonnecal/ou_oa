package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 离职申请对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Quit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 离职原因
     */
    private String reason;

    /**
     * 离职描述
     */
    private String description;

    /**
     * 离职时间
     */
    private Date quitTime;

    /**
     * 离职申请时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 离职审批人
     */
    private String approveName;

    /**
     * 离职审批时间
     */
    private String approveTime;

    /**
     * 申请状态 2 未通过，1 通过，0 未审核
     */
    private Integer status;

    /**
     * 离职申请人
     */
    @TableField("user_name")
    private String userName;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
