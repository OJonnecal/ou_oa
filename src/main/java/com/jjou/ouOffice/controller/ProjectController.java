package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Assignment;
import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 项目控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/getApplyProjectList")
    @ResponseBody
    public Result getApplyProjectList() {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0).orderByDesc("apply_time");
        return Result.ok().data("applyProjectList", projectService.list(wrapper));
    }

    @PostMapping(value = "/getProjectList")
    @ResponseBody
    public Result getProjectList() {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("create_time");
        return Result.ok().data("projectList", projectService.list(wrapper));
    }

    @PostMapping(value = "/getFailProjectList")
    @ResponseBody
    public Result getFailProjectList() {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 2).orderByDesc("apply_time");
        return Result.ok().data("failProjectList", projectService.list(wrapper));
    }

    @PostMapping("/delProject")
    @ResponseBody
    public Result delProject(@RequestBody Project project){
        boolean isSuccess = projectService.removeById(project.getId());

        if (isSuccess){
            return Result.ok().message("删除项目成功");
        }else{
            return Result.error().message("删除项目失败");
        }
    }

    @PostMapping("/updateProject")
    @ResponseBody
    public Result updateProject(@RequestBody Project project){
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("id", project.getId());
        boolean isSuccess = projectService.update(project, wrapper);

        if (isSuccess){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

    @PostMapping("/addProject")
    @ResponseBody
    public Result addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PostMapping("/agreeProject")
    @ResponseBody
    public Result agreeProject(@RequestBody Project project){
        return projectService.agreeProject(project);
    }
}

