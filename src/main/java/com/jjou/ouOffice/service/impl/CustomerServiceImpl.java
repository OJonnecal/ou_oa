package com.jjou.ouOffice.service.impl;

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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        customer.setCreateTime(sdf.format(date));
        save(customer);
        return Result.ok().message("添加客户成功！");
    }
}
