package com.xzy.service.impl;

import com.xzy.mapper.RoleMapper;
import com.xzy.domain.Role;
import com.xzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户类型查询
     * @param id 用户类型id
     * @return Role对象
     * @throws Exception
     */
    @Override
    public Role findByid(Integer id) {
        return roleMapper.selectRoleById(id);
    }
}
