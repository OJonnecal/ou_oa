package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Assignment;
import com.jjou.ouOffice.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 任务清单控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/getAssignmentInCompleteList")
    @ResponseBody
    public Result getAssignmentInCompleteList(){
        QueryWrapper<Assignment> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        return Result.ok().data("assignmentInCompleteList", assignmentService.list(wrapper));
    }

    @PostMapping("/getAssignmentCompleteList")
    @ResponseBody
    public Result getAssignmentCompleteList(){
        QueryWrapper<Assignment> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return Result.ok().data("assignmentCompleteList", assignmentService.list(wrapper));
    }

    @PostMapping("/addAssignment")
    @ResponseBody
    public Result addAssignment(@RequestBody Assignment assignment){
        return assignmentService.addAssignment(assignment);
    }

    @PostMapping("/delAssignment")
    @ResponseBody
    public Result delAssignment(@RequestBody Assignment assignment){
        boolean isSuccess = assignmentService.removeById(assignment.getId());

        if (isSuccess){
            return Result.ok().message("删除任务成功！");
        }else{
            return Result.error().message("删除任务失败！");
        }
    }

    @PostMapping("/completeAssignment")
    @ResponseBody
    public Result completeAssignment(@RequestBody Integer id){
        return assignmentService.completeAssignment(id);
    }
}

