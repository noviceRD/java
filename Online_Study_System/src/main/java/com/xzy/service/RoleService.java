package com.xzy.service;

import com.xzy.domain.Role;


public interface RoleService {

    /**
     * 根据用户类型查询
     * @param id 用户类型id
     * @return Role对象
     * @throws Exception
     */
    Role findByid(Integer id);

}
