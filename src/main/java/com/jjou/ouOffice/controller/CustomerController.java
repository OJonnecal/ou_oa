package com.jjou.ouOffice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Customer;
import com.jjou.ouOffice.entity.MeetingRoom;
import com.jjou.ouOffice.entity.Notice;
import com.jjou.ouOffice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 客户管理控制器
 *
 * @author jjou
 * @since 2023-01-03
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/getCustomerList")
    @ResponseBody
    public Result getCustomerList(){
        return Result.ok().data("customerList", customerService.list());
    }

    @PostMapping("/addCustomer")
    @ResponseBody
    public Result addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @PostMapping("/delCustomer")
    @ResponseBody
    public Result delCustomer(@RequestBody Customer customer){
        boolean isSuccess = customerService.removeById(customer.getId());

        if (isSuccess){
            return Result.ok().message("删除客户成功");
        }else{
            return Result.error().message("删除客户失败");
        }
    }

    @PostMapping("/updateCustomer")
    @ResponseBody
    public Result updateCustomer(@RequestBody Customer customer){
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("id", customer.getId());
        boolean isSuccess = customerService.update(customer, wrapper);

        if (isSuccess){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

    //TODO 条件查询客户
}

