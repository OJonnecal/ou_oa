package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", user.getAccount());
        User loginUser = baseMapper.selectOne(wrapper);
        if(loginUser == null){
            return Result.error().message("账号不存在！");
        }

        if(!loginUser.getPwd().equals(user.getPwd())){
            return Result.error().message("密码错误！");
        }
        return Result.ok().data("user", loginUser).message("登录成功！");
    }

    @Override
    public Result addUser(User user) {
        if(user.getName() == null || StringUtils.isEmpty(user.getName())){
            return Result.error().message("用户账号不能为空！");
        }
        if(user.getPwd() == null || StringUtils.isEmpty(user.getPwd())){
            return Result.error().message("用户密码不能为空！");
        }
        if(user.getPosition() == null || StringUtils.isEmpty(user.getPosition())){
            return Result.error().message("用户职位不能为空！");
        }
        if(query().eq("name", user.getName()).count() > 0){
            return Result.error().message("用户昵称不能重复！");
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(sdf.format(date));
        save(user);
        return Result.ok().message("添加用户成功！");
    }

    @Override
    public Result getUserList(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user != null && user.getName() != null && !StringUtils.isEmpty(user.getName())){
            wrapper.like("name", user.getName());
        }
        if(user != null && user.getPhone() != null && !StringUtils.isEmpty(user.getPhone())){
            wrapper.like("phone", user.getPhone());
        }
        if(user != null && user.getPosition() != null && !StringUtils.isEmpty(user.getPosition())){
            wrapper.like("position", user.getPosition());
        }
        wrapper.orderByDesc("create_time");
        return Result.ok().data("userList", list(wrapper));
    }

    @Override
    public Result updateUser(User user) {
        if(user.getName() == null || StringUtils.isEmpty(user.getName())){
            return Result.error().message("用户账号不能为空！");
        }
        if(user.getPwd() == null || StringUtils.isEmpty(user.getPwd())){
            return Result.error().message("用户密码不能为空！");
        }
        if(user.getPosition() == null || StringUtils.isEmpty(user.getPosition())){
            return Result.error().message("用户职位不能为空！");
        }
        if(query().eq("name", user.getName()).count() > 0){
            return Result.error().message("用户昵称不能重复！");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", user.getId());
        if (update(user, wrapper)){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }
}
