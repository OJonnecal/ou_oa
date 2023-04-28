package com.jjou.ouOffice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.ClockIn;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.ClockInMapper;
import com.jjou.ouOffice.service.ClockInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class ClockInServiceImpl extends ServiceImpl<ClockInMapper, ClockIn> implements ClockInService {

    @Override
    public Result addStart(String userStr) {
        User user = JSONObject.parseObject(userStr, User.class);
        if (user == null) {
            return Result.error().message("请先登录");
        }

        Integer userId = user.getId();
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.now();

        //判断是否在上班打卡指定时间
        int hour = time.getHour();
        if(!(hour <= 9 && hour >= 6)){
            return Result.error().message("不在上班打卡时间！");
        }

        //判断是否已经上班打卡
        Integer count = query().eq("user_id", userId)
                .eq("date", date)
                .eq("type", 1)
                .count();
        if(count > 0){
            return Result.error().message("请勿重复上班打卡");
        }

        ClockIn clockIn = new ClockIn();
        clockIn.setUserId(userId);
        clockIn.setTime(time);
        clockIn.setDate(date);
        clockIn.setType(1);
        if(save(clockIn)){
            return Result.ok().message("上班打卡成功");
        }
        return Result.error().message("上班打卡失败");
    }

    @Override
    public Result addEnd(String userStr) {
        User user = JSONObject.parseObject(userStr, User.class);
        if (user == null) {
            return Result.error().message("请先登录");
        }

        Integer userId = user.getId();
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.now();

        //判断是否在下班打卡指定时间
        int hour = time.getHour();
        if(hour < 17){
            return Result.error().message("不在下班打卡时间！");
        }


        //判断是否已经下班打卡
        Integer endCount = query().eq("user_id", userId)
                .eq("date", date)
                .eq("type", 2)
                .count();
        if(endCount > 0){
            return Result.error().message("请勿重复下班打卡");
        }

        ClockIn clockIn = new ClockIn();
        clockIn.setUserId(userId);
        clockIn.setTime(time);
        clockIn.setDate(date);
        clockIn.setType(2);
        if(save(clockIn)){
            return Result.ok().message("下班打卡成功");
        }
        return Result.error().message("下班打卡失败");
    }

    @Override
    public List<ClockIn> getDataByUserId(String userStr) {
        User user = JSONObject.parseObject(userStr, User.class);
        if(user != null){
            Integer userId = user.getId();
//            QueryWrapper<ClockIn> wrapper = new QueryWrapper<>();
//            wrapper.eq("user_id", userId);
            return query().eq("user_id", userId).list();
        }
        return null;
    }



}
