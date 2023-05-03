package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Contacts;
import com.jjou.ouOffice.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通讯录控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @PostMapping("/getContactsList")
    @ResponseBody
    public Result getContactsList(){
        return Result.ok().data("contactsList", contactsService.list());
    }

    @PostMapping("/addContacts")
    @ResponseBody
    public Result addContacts(@RequestBody Contacts contacts){
        return contactsService.addContacts(contacts);
    }

    @PostMapping("/delContacts")
    @ResponseBody
    public Result delContacts(@RequestBody Contacts contacts){
        boolean isSuccess = contactsService.removeById(contacts.getId());

        if (isSuccess){
            return Result.ok().message("删除联系人成功");
        }else{
            return Result.error().message("删除联系人失败");
        }
    }

    @PostMapping("/updateContacts")
    @ResponseBody
    public Result updateContacts(@RequestBody Contacts contacts){
        return contactsService.updateContacts(contacts);
    }
}

