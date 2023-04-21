package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
