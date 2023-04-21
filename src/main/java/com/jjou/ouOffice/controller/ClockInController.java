package com.jjou.ouOffice.controller;


import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.ClockIn;
import com.jjou.ouOffice.service.ClockInService;
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
@RequestMapping("/clockin")
public class ClockInController {

    @Autowired
    private ClockInService clockInService;

    @PostMapping("/addStart")
    @ResponseBody
    public Result addStart(@RequestBody String userStr){
        return clockInService.addStart(userStr);

    }

    @PostMapping("/addEnd")
    @ResponseBody
    public Result addEnd(@RequestBody String userStr){
        return clockInService.addEnd(userStr);
    }

    @ResponseBody
    @PostMapping("/getData")
    public Result getData(@RequestBody String userStr){
        List<ClockIn> list = clockInService.getDataByUserId(userStr);
        return Result.ok().data("dataList", list);
    }
}

