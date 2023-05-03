package com.jjou.ouOffice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Customer;
import com.jjou.ouOffice.mapper.CustomerMapper;
import com.jjou.ouOffice.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public Result addCustomer(Customer customer) {
        if(customer.getName() == null || StringUtils.isEmpty(customer.getName())){
            return Result.error().message("客户姓名不能为空！");
        }
        if(customer.getPhone() == null || StringUtils.isEmpty(customer.getPhone())){
            return Result.error().message("客户手机号不能为空！");
        }
        if(query().eq("name", customer.getName()).count() > 0){
            return Result.error().message("客户姓名不能重复！");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customer.setCreateTime(sdf.format(date));
        save(customer);
        return Result.ok().message("添加客户成功！");
    }

    @Override
    public Result getCustomerList(Customer customer) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        if (customer != null && customer.getName() != null
                && !StringUtils.isEmpty(customer.getName())){
            wrapper.like("name", customer.getName());
        }
        if(customer != null && customer.getPhone() != null
                && !StringUtils.isEmpty(customer.getPhone())){
            wrapper.like("phone", customer.getPhone());
        }
        wrapper.orderByDesc("create_time");
        return Result.ok().data("customerList", list(wrapper));
    }

    @Override
    public Result updateCustomer(Customer customer) {
        if(customer.getName() == null || StringUtils.isEmpty(customer.getName())){
            return Result.error().message("客户姓名不能为空！");
        }
        if(customer.getPhone() == null || StringUtils.isEmpty(customer.getPhone())){
            return Result.error().message("客户手机号不能为空！");
        }
        Customer oldCustomer = query().eq("id", customer.getId()).one();
        if(!oldCustomer.getName().equals(customer.getName())) {
            if (query().eq("name", customer.getName()).count() > 0) {
                return Result.error().message("客户姓名不能重复！");
            }
        }
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("id", customer.getId());
        if (update(customer, wrapper)){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }
}
