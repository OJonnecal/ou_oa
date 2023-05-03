package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.TopContacts;
import com.jjou.ouOffice.service.TopContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 常用联系人控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/topcontacts")
public class TopContactsController {

    @Autowired
    private TopContactsService topContactsService;

    @PostMapping("/getTopContactsList")
    @ResponseBody
    @CrossOrigin
    public Result getTopContactsList(){
        List<TopContacts> list = topContactsService.list();
        return Result.ok().data("topContactsList", list);
    }

    @PostMapping("/addTopContacts")
    @ResponseBody
    @CrossOrigin
    public Result addTopContacts(@RequestBody TopContacts topContacts){
        return topContactsService.addTopContacts(topContacts);
    }

    @PostMapping("/delTopContacts")
    @ResponseBody
    @CrossOrigin
    public Result delTopContacts(@RequestBody TopContacts topContactsId){
        boolean isSuccess = topContactsService.removeById(topContactsId.getId());
        if(isSuccess){
            return Result.ok().message("删除常用联系人成功");
        }else{
            return Result.error().message("删除常用联系人失败");
        }
    }

    @PostMapping("/updateTopContacts")
    @ResponseBody
    public Result updateTopContacts(@RequestBody TopContacts topContacts){
        return topContactsService.updateTopContacts(topContacts);
    }

}

