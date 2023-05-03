package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.LeaveMapper;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result addLeave(Leave leave) {
        if(leave.getReason() == null || StringUtils.isEmpty(leave.getReason())){
            return Result.error().message("请假原因不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        leave.setCreateTime(sdf.format(date));
        save(leave);
        return Result.ok().message("请假申请添加成功！");
    }

    @Override
    public Result agreeLeave(Leave leave) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        leave.setApproveTime((sdf.format(date)));
        if (updateById(leave)) {
            if(leave.getStatus() == 1) {
                return Result.ok().message("请假申请通过！");
            }else{
                return Result.ok().message("请假申请拒绝！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }

    @Override
    public Result getApplyLeaveList(Leave leave) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", leave.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 0).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 0).eq("user_name", leave.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("applyLeaveList", list(wrapper));
    }

    @Override
    public Result getLeaveList(Leave leave) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", leave.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 1).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 1).eq("user_name", leave.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("leaveList", list(wrapper));
    }

    @Override
    public Result getFailLeaveList(Leave leave) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", leave.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Leave> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 2).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 2).eq("user_name", leave.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("failLeaveList", list(wrapper));
    }

}
