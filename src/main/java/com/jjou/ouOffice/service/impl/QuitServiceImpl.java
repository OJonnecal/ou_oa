package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Quit;
import com.jjou.ouOffice.mapper.QuitMapper;
import com.jjou.ouOffice.service.QuitService;
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
public class QuitServiceImpl extends ServiceImpl<QuitMapper, Quit> implements QuitService {

    @Override
    public Result addQuit(Quit quit) {
        if(quit.getReason() == null || StringUtils.isEmpty(quit.getReason())){
            return Result.error().message("离职原因不能为空！");
        }
        if(quit.getQuitTime() == null || StringUtils.isEmpty(quit.getQuitTime())){
            return Result.error().message("离职时间不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        quit.setCreateTime(sdf.format(date));
        save(quit);
        return Result.ok().message("离职申请添加成功！");
    }

    @Override
    public Result agreeQuit(Quit quit) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        quit.setApproveTime((sdf.format(date)));
        boolean isSuccess = updateById(quit);

        if (isSuccess) {
            if(quit.getStatus() == 1) {
                return Result.ok().message("离职申请通过！");
            }else{
                return Result.ok().message("离职申请拒绝！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }
}
