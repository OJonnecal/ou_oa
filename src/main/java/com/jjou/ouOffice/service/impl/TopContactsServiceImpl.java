package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.TopContacts;
import com.jjou.ouOffice.mapper.TopContactsMapper;
import com.jjou.ouOffice.service.TopContactsService;
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
public class TopContactsServiceImpl extends ServiceImpl<TopContactsMapper, TopContacts> implements TopContactsService {

    @Override
    public Result addTopContacts(TopContacts topContacts) {
        if (topContacts.getName().isEmpty() || topContacts.getName() == null) {
            return Result.error().message("联系人名称不能为空");
        }else if(topContacts.getPhone().isEmpty() || topContacts.getPhone() == null){
            return Result.error().message("联系人手机号不能为空");
        }
        if(save(topContacts)){
            return Result.ok().message("新增常用联系人成功");
        }
        return Result.error().message("新增常用联系人失败");
    }
}
