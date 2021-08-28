package com.xzy.service;

import com.xzy.domain.Course;

import java.util.List;


public interface CourseService {
    /**
     * 更新课程
     * @param course course对象
     */
    void updateById(Course course) ;

    /**
     * 删除课程
     * @param id 课程id
     * @return 删除是否成功
     */
    Boolean removeById(Integer id) ;

    /**
     * 分页查询课程
     * @param toPageNo 页码
     */
    List<Course> findByPaging(Integer toPageNo) ;

    /**
     * 添加课程
     * @param course course对象
     * @return 添加是否成功
     */
    int save(Course course) ;

    /**
     * 获取课程数目
     * @return 课程数目
     */
    int getCountCourse() ;

    /**
     * 根据课程id查询课程
     * @param id 课程id
     */
    Course findById(Integer id) ;

    /**
     * 根据教师id查找课程
     * @param id 教师id
     * @return
     */
    List<Course> findByTeacherID(Integer id) ;

    /**
     * 根据id查询单条课程信息
     * @param id
     * @return
     */
    Course queryCourseById(int id);

    /**
     * 向数据库插入上传文件的名称
     * @param course
     */
    void addCourse(Course course);

    void updateCourseVideo(Course course);


}
