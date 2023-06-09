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
 * 任务清单对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 任务紧急程度 1 紧急 2 正常 3 不紧急
     */
    private Integer level;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 任务创建时间
     */
    @TableField("create_time")
    private String createTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
