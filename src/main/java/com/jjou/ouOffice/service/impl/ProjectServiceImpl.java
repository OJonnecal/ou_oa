package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.mapper.ProjectMapper;
import com.jjou.ouOffice.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public List<Project> getProjectList() {
        return list();
    }

    @Override
    public Result addProject(Project project) {
        if(project.getName() == null || StringUtils.isEmpty(project.getName())){
            return Result.error().message("项目名称不能为空！");
        }
        if(project.getUserName() == null || StringUtils.isEmpty(project.getUserName())){
            return Result.error().message("项目负责人不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        project.setCreateTime(sdf.format(date));
        save(project);
        return Result.ok().message("项目审批通过！");
    }
}
