package com.xzy.service;

import com.xzy.domain.Student;

import java.util.List;


public interface StudentService {

    /**
     * 更新学生信息
     * @param student Student对象
     */
    void updateById(Student student) ;

    /**
     * 删除学生信息
     * @param id 学生id
     * @return 删除是否成功
     */
    Boolean removeById(Integer id) ;

    /**
     * 分页查询学生信息
     * @param toPageNo 页码
     */
    List<Student> findByPaging(Integer toPageNo) ;

    /**
     * 添加学生信息
     * @param student Studen对象
     * @return 添加是否成功
     */
    int save(Student student) ;

    /**
     * 获取学生人数
     * @return 学生人数
     * @
     */
    int getCountStudent() ;

    /**
     * 查询学生信息
     * @param id 学生id
     */
    Student findById(Integer id) ;

    /**
     * 根据名字查询学生信息
     * @param name
     */
    List<Student> findByName(String name) ;

}
