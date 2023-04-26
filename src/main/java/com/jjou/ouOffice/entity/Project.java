package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    private String title;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目进度
     */
    private Integer rate;

    /**
     * 项目负责人
     */
    @TableField("user_name")
    private String userName;

    /**
     * 项目申请时间
     */
    private String applyTime;

    /**
     * 项目创建时间
     */
    private String createTime;

    /**
     * 状态 1（true）已审核， 0（false）未审核
     */
    private Integer status;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
