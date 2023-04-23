package com.jjou.ouOffice.service.impl;

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
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", user.getId()).eq("pwd", user.getPwd());
        User loginUser = baseMapper.selectById(user.getId());

        if(loginUser == null || !loginUser.getPwd().equals(user.getPwd())){
            return Result.error();
        }
        return Result.ok().data("user", loginUser);
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
            return Result.error().message("用户姓名已存在！");
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setCreateTime(sdf.format(date));
        save(user);
        return Result.ok().message("添加用户成功！");
    }
}
