package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.entity.Message;
import com.jjou.ouOffice.mapper.MessageMapper;
import com.jjou.ouOffice.service.MessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
