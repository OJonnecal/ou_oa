package com.jjou.ouOffice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.MeetingRoom;
import com.jjou.ouOffice.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 会议室控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/meetingroom")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/addMeetingRoom")
    @ResponseBody
    public Result addMeetingRoom(@RequestBody MeetingRoom meetingRoom){
        return meetingRoomService.addMeetingRoom(meetingRoom);
    }

    @PostMapping("/getMeetingRoomList")
    @ResponseBody
    public Result getMeetingRoomList(@RequestBody MeetingRoom meetingRoom){
        return meetingRoomService.getMeetingRoomList(meetingRoom);
    }

    @PostMapping("/updateMeetingRoom")
    @ResponseBody
    public Result updateMeetingRoom(@RequestBody MeetingRoom meetingRoom){
        QueryWrapper<MeetingRoom> wrapper = new QueryWrapper<>();
        wrapper.eq("name", meetingRoom.getName());
        boolean isSuccess = meetingRoomService.update(meetingRoom, wrapper);

        if (isSuccess){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

    @PostMapping("/delMeetingRoom")
    @ResponseBody
    public Result delMeetingRoom(@RequestBody MeetingRoom meetingRoom){
        boolean isSuccess = meetingRoomService.removeById(meetingRoom.getId());

        if (isSuccess){
            return Result.ok().message("删除公告成功");
        }else{
            return Result.error().message("删除公告失败");
        }
    }

}

