package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Contacts;
import com.jjou.ouOffice.mapper.ContactsMapper;
import com.jjou.ouOffice.service.ContactsService;
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
public class ContactsServiceImpl extends ServiceImpl<ContactsMapper, Contacts> implements ContactsService {

    @Override
    public Result addContacts(Contacts contacts) {
        return null;
    }
}
