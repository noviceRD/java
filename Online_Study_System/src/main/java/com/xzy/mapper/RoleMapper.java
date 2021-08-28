package com.xzy.mapper;

import com.xzy.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    Role selectRoleById(Integer id);
}