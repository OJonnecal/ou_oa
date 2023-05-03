package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Leave;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface LeaveService extends IService<Leave> {

    Result addLeave(Leave leave);

    Result agreeLeave(Leave leave);

    Result getApplyLeaveList(Leave leave);

    Result getLeaveList(Leave leave);

    Result getFailLeaveList(Leave leave);
}
