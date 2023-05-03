package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.ProjectMapper;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Resource
    private UserMapper userMapper;

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
        if(save(project)){
            return Result.ok().message("项目申请添加成功！");
        }else{
            return Result.error().message("项目添加失败！");
        }

    }

    @Override
    public Result agreeProject(Project project) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(project.getStatus() == 0){
            project.setApplyTime(sdf.format(date));
        }else if(project.getStatus() == 1){
            project.setCreateTime(sdf.format(date));
        }
        if (updateById(project)) {
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
        if(project.getTitle() == null || StringUtils.isEmpty(project.getTitle())){
            return Result.error().message("项目名称不能为空！");
        }
        if(project.getUserName() == null || StringUtils.isEmpty(project.getUserName())){
            return Result.error().message("项目负责人不能为空！");
        }
        if(project.getRate() != null && (project.getRate() < 0 || project.getRate() > 100)){
            return Result.error().message("项目进度范围为0-100");
        }
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("id", project.getId());
        if (update(project, wrapper)){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

    @Override
    public Result getProjectList(Project project) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        if (project != null && project.getTitle() != null && !StringUtils.isEmpty(project.getTitle())){
            wrapper.like("title", project.getTitle());
        }
        if(project != null && project.getUserName() != null && !StringUtils.isEmpty(project.getUserName())){
            wrapper.like("user_name", project.getUserName());
        }
        wrapper.eq("status", 1).orderByDesc("create_time");
        return Result.ok().data("projectList", list(wrapper));
    }

    @Override
    public Result getApplyProjectList(Project project) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", project.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 0).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 0).eq("user_name", project.getUserName()).orderByDesc("apply_time");
        }
        return Result.ok().data("applyProjectList", list(wrapper));
    }

    @Override
    public Result getFailProjectList(Project project) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", project.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 2).orderByDesc("approve_time");
        }else {
            wrapper.eq("status", 2).eq("user_name", project.getUserName()).orderByDesc("approve_time");
        }
        return Result.ok().data("failProjectList", list(wrapper));
    }
}
