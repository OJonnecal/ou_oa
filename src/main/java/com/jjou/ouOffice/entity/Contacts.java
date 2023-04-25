package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通讯录对象
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("contacts")
public class Contacts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 联系人姓名
     */
    @TableField("name")
    private String name;

    /**
     * 联系人电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
