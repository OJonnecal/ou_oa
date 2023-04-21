package com.jjou.ouOffice.controller;


import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 项目申请/审批控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/getProjectList")
    @ResponseBody
    public Result getProjectList() {
        List<Project> list = projectService.getProjectList();
        return Result.ok().data("projectList", list);
    }

}

