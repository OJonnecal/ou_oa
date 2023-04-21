package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.MeetingRoom;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface MeetingRoomService extends IService<MeetingRoom> {

    Result addMeetingRoom(MeetingRoom meetingRoom);
}
