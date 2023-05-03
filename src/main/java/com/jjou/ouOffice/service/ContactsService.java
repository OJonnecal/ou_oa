package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Contacts;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface ContactsService extends IService<Contacts> {

    Result addContacts(Contacts contacts);

    Result updateContacts(Contacts contacts);

    Result getContactsList(Contacts contacts);
}
