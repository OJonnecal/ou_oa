package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Contacts;
import com.jjou.ouOffice.mapper.ContactsMapper;
import com.jjou.ouOffice.service.ContactsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class ContactsServiceImpl extends ServiceImpl<ContactsMapper, Contacts> implements ContactsService {

    @Override
    public Result addContacts(Contacts contacts) {
        if(contacts.getName() == null || StringUtils.isEmpty(contacts.getName())){
            return Result.error().message("联系人姓名不能为空！");
        }
        if(contacts.getPhone() == null || StringUtils.isEmpty(contacts.getPhone())){
            return Result.error().message("联系人手机号不能为空！");
        }
        save(contacts);
        return Result.ok().message("添加联系人成功！");
    }
}
