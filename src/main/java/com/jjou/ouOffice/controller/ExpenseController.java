package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Expense;
import com.jjou.ouOffice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报销申请/审批控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(value = "/getApplyExpenseList")
    @ResponseBody
    public Result getApplyExpenseList(@RequestBody Expense expense) {
        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0).eq("user_id", expense.getUserId()).orderByDesc("create_time");
        return Result.ok().data("applyExpenseList", expenseService.list(wrapper));
    }

    @PostMapping(value = "/getExpenseList")
    @ResponseBody
    public Result getExpenseList(@RequestBody Expense expense) {
        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).eq("user_id", expense.getUserId()).orderByDesc("approve_time");
        return Result.ok().data("expenseList", expenseService.list(wrapper));
    }

    @PostMapping(value = "/getFailExpenseList")
    @ResponseBody
    public Result getFailExpenseList(@RequestBody Expense expense) {
        QueryWrapper<Expense> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 2).eq("user_id", expense.getUserId()).orderByDesc("approve_time");
        return Result.ok().data("failExpenseList", expenseService.list(wrapper));
    }

    @PostMapping("/delExpense")
    @ResponseBody
    public Result delExpense(@RequestBody Expense expense){
        boolean isSuccess = expenseService.removeById(expense.getId());

        if (isSuccess){
            return Result.ok().message("删除报销申请成功！");
        }else{
            return Result.error().message("删除报销申请失败！");
        }
    }


    @PostMapping("/addExpense")
    @ResponseBody
    public Result addExpense(@RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }

    /**
     * 报销审批
     * @param expense
     * @return
     */
    @PostMapping("/agreeExpense")
    @ResponseBody
    public Result agreeLeave(@RequestBody Expense expense){
        return expenseService.agreeExpense(expense);
    }

}

