package com.xzy.service.impl;


import com.xzy.mapper.CourseMapper;
import com.xzy.mapper.StudentCourseMapper;
import com.xzy.domain.*;
import com.xzy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;


    @Override
    public void updateById(Course course) {
        courseMapper.updateCourseById(course);
    }

    @Override
    public Boolean removeById(Integer id) {
        //查询是否有学生选择这门课程
        List<StudentCourse> list=studentCourseMapper.selectCourseWithStudent(id);
        if(list.size()==0){
            //没有学生学则，可以删除
            courseMapper.deleteCourseById(id);
            return true;
        }
        //有学生选择就不能删除
        return false;
    }

    @Override
    public List<Course> findByPaging(Integer toPageNo) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<Course> list = courseMapper.selectCourseByPaging(pagingVO);
        return list;
    }

    @Override
    public int getCountCourse() {
        return courseMapper.getCountCourse();
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.selectCourseById(id);
    }

    @Override
    public List<Course> findByTeacherID(Integer id) {
        return courseMapper.selectCourseByTeacherId(id);
    }

    @Override
    public Course queryCourseById(int id) {
        return courseMapper.selectOneCourseById(id);
    }

    @Override
    public void addCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    @Override
    public void updateCourseVideo(Course course) {
        courseMapper.updateCourseWithVideo(course);
    }


    @Override
    public int save(Course course) {
        return courseMapper.insertCourse(course);
    }
}
