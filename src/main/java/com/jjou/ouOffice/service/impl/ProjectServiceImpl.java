package com.jjou.ouOffice.service.impl;

import com.jjou.ouOffice.entity.Project;
import com.jjou.ouOffice.mapper.ProjectMapper;
import com.jjou.ouOffice.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public List<Project> getProjectList() {
        return list();
    }
}
