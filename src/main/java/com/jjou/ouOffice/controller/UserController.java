package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * 用户控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/getUserList")
    @ResponseBody
    public Result getUserList(@RequestBody User user) {
        return userService.getUserList(user);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/delUser")
    @ResponseBody
    public Result delUser(@RequestBody User user) {
        boolean isSuccess = userService.removeById(user.getId());

        if (isSuccess){
            return Result.ok().message("删除用户成功");
        }else{
            return Result.error().message("删除用户失败");
        }
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}

