package com.jjou.ouOffice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("meeting_notice")
public class MeetingNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 会议开始时间
     */
    @TableField("begin_time")
    private LocalDateTime beginTime;

    /**
     * 会议结束时间
     */
    @TableField("begin_time")
    private LocalDateTime endTime;

    /**
     * 会议地点
     */
    private String place;

    /**
     * 会议被通知人id
     */
    @TableField("notified_person_id")
    private Integer notifiedPersonId;

    /**
     * 会议通知人id
     */
    @TableField("notifier_id")
    private Integer notifierId;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
