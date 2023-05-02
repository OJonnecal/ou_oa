package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.mapper.LeaveMapper;
import com.jjou.ouOffice.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Override
    public Result addLeave(Leave leave) {
        if(leave.getReason() == null || StringUtils.isEmpty(leave.getReason())){
            return Result.error().message("请假原因不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        leave.setCreateTime(sdf.format(date));
        save(leave);
        return Result.ok().message("请假申请添加成功！");
    }

    @Override
    public Result agreeLeave(Leave leave) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        leave.setApproveTime((sdf.format(date)));
        if (updateById(leave)) {
            if(leave.getStatus() == 1) {
                return Result.ok().message("请假申请通过！");
            }else{
                return Result.ok().message("请假申请拒绝！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }

}
