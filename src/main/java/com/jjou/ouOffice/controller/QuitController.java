package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Quit;
import com.jjou.ouOffice.service.QuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 离职申请/审批控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/quit")
public class QuitController {

    @Autowired
    private QuitService quitService;

    @PostMapping(value = "/getApplyQuitList")
    @ResponseBody
    public Result getApplyQuitList(@RequestBody Quit quit) {
        return quitService.getApplyQuitList(quit);
    }

    @PostMapping(value = "/getQuitList")
    @ResponseBody
    public Result getQuitList(@RequestBody Quit quit) {
        return quitService.getQuitList(quit);
    }

    @PostMapping(value = "/getFailQuitList")
    @ResponseBody
    public Result getFailQuitList(@RequestBody Quit quit) {
        return quitService.getFailQuitList(quit);
    }

    @PostMapping("/delQuit")
    @ResponseBody
    public Result delQuit(@RequestBody Quit quit){
        boolean isSuccess = quitService.removeById(quit.getId());

        if (isSuccess){
            return Result.ok().message("删除请假申请成功！");
        }else{
            return Result.error().message("删除请假申请失败！");
        }
    }


    @PostMapping("/addQuit")
    @ResponseBody
    public Result addQuit(@RequestBody Quit quit){
        return quitService.addQuit(quit);
    }

    /**
     * 离职审批
     * @param quit
     * @return
     */
    @PostMapping("/agreeQuit")
    @ResponseBody
    public Result agreeQuit(@RequestBody Quit quit){
        return quitService.agreeQuit(quit);
    }

}

