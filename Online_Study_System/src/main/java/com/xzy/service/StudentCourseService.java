package com.xzy.service;

import com.xzy.domain.Course;
import com.xzy.domain.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StudentCourseService {

    /**
     * 根据课程ID查询课程
     * @param id 课程id
     */
    List<StudentCourse> findByCourseID(Integer id) ;

    /**
     * 根据学生id查找课程
     * @param id 学生id

     */
    List<StudentCourse> findByStudentID(Integer id) ;

    /**
     * 根据学生id查找课程
     * @param id 学生id
     */
    List<StudentCourse> findCourseByStudentID(Integer id);

    /**
     * 获取课程学生人数
     * @param id 课程id
     * @return 学生人数
     */
    Integer countByCourseID(Integer id) ;

    /**
     * 查询单条选课信息
     * @param courseId 课程id
     * @param studentId 学生id
     */
    StudentCourse findOne(Integer courseId,Integer studentId) ;

    /**
     * 更新选课信息
     * @param studentCourse studentCourse对象
     * @
     */
    void update(StudentCourse studentCourse) ;

    /**
     * 保存选课信息
     * @param studentCourse studentCourse对象
     */
    int save(StudentCourse studentCourse) ;

    /**
     * 删除选课信息
     * @param id
     */
    void remove(Integer id) ;

    /**
     * 插入成绩
     * @param studentCourse
     */
    void updateScore(StudentCourse studentCourse);

    /**
     * 退课操作
     * @param id
     * @param stuId
     */
    void removeChoseCourse(Integer id, int stuId);
}
