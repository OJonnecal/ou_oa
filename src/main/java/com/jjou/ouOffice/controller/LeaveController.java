package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result getApplyLeaveList() {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        return Result.ok().data("applyProjectList", leaveService.list(wrapper));
    }

    @PostMapping(value = "/getLeaveList")
    @ResponseBody
    public Result getLeaveList() {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return Result.ok().data("projectList", leaveService.list(wrapper));
    }

    @PostMapping(value = "/getFailLeaveList")
    @ResponseBody
    public Result getFailLeaveList() {
        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 2);
        return Result.ok().data("failProjectList", leaveService.list(wrapper));
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

    @PostMapping("/agreeLeave")
    @ResponseBody
    public Result agreeLeave(@RequestBody Leave leave){
        return leaveService.agreeLeave(leave);
    }

}

