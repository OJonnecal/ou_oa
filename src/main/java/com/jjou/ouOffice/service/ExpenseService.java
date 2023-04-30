package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Expense;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jjou.ouOffice.entity.Leave;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface ExpenseService extends IService<Expense> {

    Result addExpense(Expense expense);

    Result agreeExpense(Expense expense);
}
