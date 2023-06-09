package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface UserService extends IService<User> {

    Result login(User user);

    Result addUser(User user);

    Result getUserList(User user);

    Result updateUser(User user);
}
