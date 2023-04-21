package com.jjou.ouOffice.controller;


import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
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
    public Result getUserList() {
        List<User> list = userService.list();
        return Result.ok().data("userList", list);
    }
}

