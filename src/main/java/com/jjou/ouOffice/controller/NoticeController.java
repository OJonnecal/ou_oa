package com.jjou.ouOffice.controller;


import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Notice;
import com.jjou.ouOffice.service.NoticeService;
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
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/getNoticeList")
    @ResponseBody
    public Result getNoticeList(){
        List<Notice> list = noticeService.list();

        return Result.ok().data("noticeList", list);
    }

    @PostMapping("/addNotice")
    @ResponseBody
    public Result addNotice(@RequestBody Notice notice){
        return noticeService.addNotice(notice);
    }

    @PostMapping("/delNotice")
    @ResponseBody
    public Result delNotice(@RequestBody Notice notice){
        Boolean isSuccess = noticeService.removeById(notice.getId());

        if (isSuccess){
            return Result.ok().message("删除公告成功");
        }else{
            return Result.error().message("删除公告失败");
        }
    }

}

