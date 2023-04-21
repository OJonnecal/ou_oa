package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Notice;
import com.jjou.ouOffice.mapper.NoticeMapper;
import com.jjou.ouOffice.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public Result addNotice(Notice notice) {
        if (notice.getTitle().isEmpty() || notice.getTitle() == null) {
            return Result.error().message("公告名称不能为空");
        }
        if(save(notice)){
            return Result.ok().message("新增公告成功");
        }
        return Result.error().message("新增公告失败");
    }
}
