package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * 请假申请/审批控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "/getApplyLeaveList")
    @ResponseBody
    public Result getApplyLeaveList(@RequestBody Leave leave) {
        return leaveService.getApplyLeaveList(leave);
    }

    @PostMapping(value = "/getLeaveList")
    @ResponseBody
    public Result getLeaveList(@RequestBody Leave leave) {
        return leaveService.getLeaveList(leave);
    }

    @PostMapping(value = "/getFailLeaveList")
    @ResponseBody
    public Result getFailLeaveList(@RequestBody Leave leave) {
        return leaveService.getFailLeaveList(leave);
    }

    @PostMapping("/delLeave")
    @ResponseBody
    public Result delLeave(@RequestBody Leave leave){
        boolean isSuccess = leaveService.removeById(leave.getId());

        if (isSuccess){
            return Result.ok().message("删除请假申请成功！");
        }else{
            return Result.error().message("删除请假申请失败！");
        }
    }


    @PostMapping("/addLeave")
    @ResponseBody
    public Result addLeave(@RequestBody Leave leave){
        return leaveService.addLeave(leave);
    }

    /**
     * 请假审批
     * @param leave
     * @return
     */
    @PostMapping("/agreeLeave")
    @ResponseBody
    public Result agreeLeave(@RequestBody Leave leave){
        return leaveService.agreeLeave(leave);
    }
}

