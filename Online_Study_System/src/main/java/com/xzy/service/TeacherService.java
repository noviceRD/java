package com.xzy.service;

import com.xzy.domain.Teacher;

import java.util.List;


public interface TeacherService {
    /**
     * 更新教师信息
     * @param teacher
     */
    void updateById(Teacher teacher) ;

    /**
     * 删除教师信息
     * @param id
     * @return
     */
    boolean removeById(Integer id) ;

    /**
     * 分页查询教师信息
     * @param toPageNo
     * @return
     */
    List<Teacher> findByPaging(Integer toPageNo) ;

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    int save(Teacher teacher) ;

    /**
     * 查询教师数量
     * @return
     */
    int getCountTeacher() ;

    /**
     * 根据教师id查询教师信息
     * @param id
     * @return
     */
    Teacher findById(Integer id) ;

    /**
     * 根据教师姓名查询教师信息
     * @param name
     * @return
     */
    List<Teacher> findByName(String name) ;

    /**
     * 查询所有教师信息
     * @return
     */
    List<Teacher> findAll() ;
}
