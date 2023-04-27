package com.jjou.ouOffice.service;

import com.jjou.ouOffice.common.Result;
import com.jjou.ouOffice.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Properties;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jjou
 * @since 2023-01-03
 */
public interface ProjectService extends IService<Project> {

    Result addProject(Project project);

    Result agreeProject(Project project);

    Result updateProject(Project project);
}
