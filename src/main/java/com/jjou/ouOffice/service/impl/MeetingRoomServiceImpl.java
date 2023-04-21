package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.MeetingRoom;
import com.jjou.ouOffice.mapper.MeetingRoomMapper;
import com.jjou.ouOffice.service.MeetingRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class MeetingRoomServiceImpl extends ServiceImpl<MeetingRoomMapper, MeetingRoom> implements MeetingRoomService {

    @Override
    public Result addMeetingRoom(MeetingRoom meetingRoom) {
        if(meetingRoom.getName() == null || meetingRoom.getName().isEmpty()){
            return Result.error().message("会议室名称不能为空");
        }
        Integer count = query().eq("name", meetingRoom.getName()).count();
        if(count >= 1){
            return Result.error().message("会议室名称已存在，不能重复");
        }
        if(meetingRoom.getStatus() == null || meetingRoom.getStatus().isEmpty()){
            return Result.error().message("会议室状态不能为空");
        }

        if(save(meetingRoom)){
            return Result.ok().message("新增会议室成功");
        }
        return Result.error().message("新增会议室失败");
    }
}
