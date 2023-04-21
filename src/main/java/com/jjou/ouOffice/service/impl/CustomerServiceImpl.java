package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.entity.Customer;
import com.jjou.ouOffice.mapper.CustomerMapper;
import com.jjou.ouOffice.service.CustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
