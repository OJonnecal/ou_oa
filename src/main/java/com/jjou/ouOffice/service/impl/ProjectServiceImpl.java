package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Result addProject(Project project) {
        if(project.getTitle() == null || StringUtils.isEmpty(project.getTitle())){
            return Result.error().message("项目名称不能为空！");
        }
        if(project.getUserName() == null || StringUtils.isEmpty(project.getUserName())){
            return Result.error().message("项目负责人不能为空！");
        }
        if(project.getRate() != null && (project.getRate() < 0 || project.getRate() > 100)){
            return Result.error().message("项目进度范围为0-100");
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(project.getStatus() == 0){
            project.setApplyTime(sdf.format(date));
        }else if(project.getStatus() == 1){
            project.setCreateTime(sdf.format(date));
        }
        save(project);
        return Result.ok().message("项目申请添加成功！");
    }

    @Override
    public Result agreeProject(Project project) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setApproveTime((sdf.format(date)));
        boolean isSuccess = updateById(project);

        if (isSuccess) {
            if(project.getStatus() == 1) {
                return Result.ok().message("项目通过审批！");
            }else{
                return Result.ok().message("项目未通过审批！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }

    @Override
    public Result updateProject(Project project) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("id", project.getId());
        if(project.getRate() != null && (project.getRate() < 0 || project.getRate() > 100)){
            return Result.error().message("项目进度范围为0-100");
        }
        boolean isSuccess = update(project, wrapper);
        if (isSuccess){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }
}
