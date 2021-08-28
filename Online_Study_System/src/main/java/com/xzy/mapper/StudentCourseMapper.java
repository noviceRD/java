package com.xzy.mapper;

import com.xzy.domain.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseMapper {

    List<StudentCourse> selectCourseWithStudent(Integer id);

    List<StudentCourse> selectStudentWithCourseId(Integer id);

    List<StudentCourse> selectCourseByStudentId(Integer id);

    Integer selectCount(Integer id);

    StudentCourse selectOneStudentWithCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    void update(StudentCourse studentCourse);

    int insertStudentCourse(StudentCourse studentCourse);

    void deleteStudentCourse(Integer id);

    List<StudentCourse> selectByStudentId(Integer id);

    void updateScore(StudentCourse studentCourse);

    void deleteChoseCourse(@Param("id") Integer id, @Param("stuId") int stuId);
}