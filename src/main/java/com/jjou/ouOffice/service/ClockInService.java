package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.ClockIn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface ClockInService extends IService<ClockIn> {

    Result addStart(String userStr);

    Result addEnd(String userStr);

    List<ClockIn> getDataByUserId(String userStr);
}
