package com.xzy.mapper;

import com.xzy.domain.Course;
import com.xzy.domain.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    void updateCourseById(Course course);

    void deleteCourseById(Integer id);

    List<Course> selectCourseByPaging(PagingVO pagingVO);

    int getCountCourse();

    Course selectCourseById(Integer id);

    List<Course> selectCourseByTeacherId(Integer id);

    int insertCourse(Course course);

    Course selectOneCourseById(int id);

    void updateCourseWithVideo(Course course);

    List<Course> selectVideo(Integer page);

    void insertCourseWithVideo(Course course, Integer teacherId);
}