package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Assignment;
import com.jjou.ouOffice.mapper.AssignmentMapper;
import com.jjou.ouOffice.service.AssignmentService;
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
public class AssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements AssignmentService {

    @Override
    public Result addAssignment(Assignment assignment) {
        if(assignment.getTitle() == null || StringUtils.isEmpty(assignment.getTitle())){
            return Result.error().message("任务标题不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        assignment.setCreateTime(sdf.format(date));
        save(assignment);
        return Result.ok().message("添加任务成功！");
    }

    @Override
    public Result completeAssignment(Integer id) {
        Assignment assignment = query().eq("id", id).one();
        assignment.setStatus(1);
        if (updateById(assignment)){
            return Result.ok().message("恭喜你完成了任务！");
        }else{
            return Result.error().message("完成任务失败！");
        }
    }
}
