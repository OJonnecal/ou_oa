package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Expense;
import com.jjou.ouOffice.entity.Leave;
import com.jjou.ouOffice.entity.User;
import com.jjou.ouOffice.mapper.ExpenseMapper;
import com.jjou.ouOffice.mapper.UserMapper;
import com.jjou.ouOffice.service.ExpenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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

    @Resource
    private UserMapper userMapper;

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

    @Override
    public Result getApplyExpenseList(Expense expense) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", expense.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 0).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 0).eq("user_name", expense.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("applyExpenseList", list(wrapper));
    }

    @Override
    public Result getExpenseList(Expense expense) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", expense.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 1).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 1).eq("user_name", expense.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("expenseList", list(wrapper));
    }

    @Override
    public Result getFailExpenseList(Expense expense) {
        //获取登录用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", expense.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        //判断用户是否是管理员
        if(user != null && user.getPermission() == 1){
            wrapper.eq("status", 2).orderByDesc("create_time");
        }else {
            wrapper.eq("status", 2).eq("user_name", expense.getUserName()).orderByDesc("create_time");
        }
        return Result.ok().data("failExpenseList", list(wrapper));
    }
}
