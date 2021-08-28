package com.xzy.mapper;

import com.xzy.domain.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUserByName(String name);

    User selectUserById(Integer id);

    void insertUser(User user);

    void deleteUserById(Integer id);

    void updateUserByName(@Param("name") String name, @Param("user") User user);

    void updateUserById(@Param("id") Integer id, @Param("user") User user);
}