package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Expense;
import com.jjou.ouOffice.entity.Quit;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.QuitMapper;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.QuitService;
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
public class QuitServiceImpl extends ServiceImpl<QuitMapper, Quit> implements QuitService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result addQuit(Quit quit) {
        if(quit.getReason() == null || StringUtils.isEmpty(quit.getReason())){
            return Result.error().message("离职原因不能为空！");
        }
        if(quit.getQuitTime() == null || StringUtils.isEmpty(quit.getQuitTime())){
            return Result.error().message("离职时间不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        quit.setCreateTime(sdf.format(date));
        save(quit);
        return Result.ok().message("离职申请添加成功！");
    }

    @Override
    public Result agreeQuit(Quit quit) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        quit.setApproveTime((sdf.format(date)));
        boolean isSuccess = updateById(quit);

        if (isSuccess) {
            if(quit.getStatus() == 1) {
                return Result.ok().message("离职申请通过！");
            }else{
                return Result.ok().message("离职申请拒绝！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }

    @Override
    public Result getApplyQuitList(Quit quit) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", quit.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Quit> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 0).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 0).eq("user_name", quit.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("applyQuitList", list(wrapper));
    }

    @Override
    public Result getQuitList(Quit quit) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", quit.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Quit> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 1).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 1).eq("user_name", quit.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("quitList", list(wrapper));
    }

    @Override
    public Result getFailQuitList(Quit quit) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", quit.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Quit> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 2).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 2).eq("user_name", quit.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("failQuitList", list(wrapper));
    }
}
