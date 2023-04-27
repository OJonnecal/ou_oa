package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Expense;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.mapper.ExpenseMapper;
import com.jjou.ouOffice.service.ExpenseService;
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
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements ExpenseService {

    @Override
    public Result addExpense(Expense expense) {
        if(expense.getReason() == null || StringUtils.isEmpty(expense.getReason())){
            return Result.error().message("报销原因不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        expense.setCreateTime(sdf.format(date));
        save(expense);
        return Result.ok().message("报销申请添加成功！");
    }

    @Override
    public Result agreeExpense(Expense expense) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        expense.setApproveTime((sdf.format(date)));
        boolean isSuccess = updateById(expense);

        if (isSuccess) {
            if(expense.getStatus() == 1) {
                return Result.ok().message("报销申请通过！");
            }else{
                return Result.ok().message("报销申请拒绝！");
            }
        }else{
            return Result.error().message("审批失败！");
        }
    }
}
