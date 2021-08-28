package com.xzy.service;

import com.xzy.domain.User;
import org.apache.ibatis.annotations.Param;


public interface UserService {

    /**
     * 查找登陆信息
     * @param name 用户名
     * @return
     */
    User findByName(String name);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 添加登录信息
     * @param user User对象
     */
    void save(User user) ;

    /**
     * 删除用户信息
     * @param id
     */
    void removeById(Integer id) ;

    /**
     * 更新用户信息
     * @param name 用户名
     * @param user User对象
     */
    void updateByName(@Param("name") String name, @Param("user") User user);

    /**
     * 根据id更新
     * @param id
     * @param user
     */
    void updateById(@Param("id") Integer id, @Param("user") User user);

}
