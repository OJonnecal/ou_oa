package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Contacts;
import com.jjou.ouOffice.entity.Customer;
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
        if(query().eq("name", contacts.getName()).count() > 0){
            return Result.error().message("联系人姓名不能重复！");
        }
        save(contacts);
        return Result.ok().message("添加联系人成功！");
    }

    @Override
    public Result updateContacts(Contacts contacts) {
        if(contacts.getName() == null || StringUtils.isEmpty(contacts.getName())){
            return Result.error().message("联系人姓名不能为空！");
        }
        if(contacts.getPhone() == null || StringUtils.isEmpty(contacts.getPhone())){
            return Result.error().message("联系人手机号不能为空！");
        }
        if(query().eq("name", contacts.getName()).count() > 0){
            return Result.error().message("联系人姓名不能重复！");
        }
        QueryWrapper<Contacts> wrapper = new QueryWrapper<>();
        wrapper.eq("id", contacts.getId());
        if (update(contacts, wrapper)){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

    @Override
    public Result getContactsList(Contacts contacts) {
        QueryWrapper<Contacts> wrapper = new QueryWrapper<>();
        if (contacts != null && contacts.getName() != null
                && !StringUtils.isEmpty(contacts.getName())){
            wrapper.like("name", contacts.getName());
        }
        if(contacts != null && contacts.getPhone() != null
                && !StringUtils.isEmpty(contacts.getPhone())){
            wrapper.like("phone", contacts.getPhone());
        }
        return Result.ok().data("contactsList", list(wrapper));
    }
}
