package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Quit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface QuitService extends IService<Quit> {

    Result addQuit(Quit quit);

    Result agreeQuit(Quit quit);
}
