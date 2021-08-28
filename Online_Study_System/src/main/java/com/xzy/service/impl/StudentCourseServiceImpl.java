package com.xzy.service.impl;

import com.xzy.mapper.StudentCourseMapper;
import com.xzy.domain.StudentCourse;
import com.xzy.mapper.StudentMapper;
import com.xzy.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public List<StudentCourse> findByCourseID(Integer id) {
        return studentCourseMapper.selectStudentWithCourseId(id);
    }

    @Override
    public List<StudentCourse> findByStudentID(Integer id) {
        return studentCourseMapper.selectByStudentId(id);
    }

    @Override
    public List<StudentCourse> findCourseByStudentID(Integer id) {
        return studentCourseMapper.selectCourseByStudentId(id);
    }

    @Override
    public Integer countByCourseID(Integer id) {
        return studentCourseMapper.selectCount(id);
    }

    @Override
    public StudentCourse findOne(Integer courseId, Integer studentId) {
        return studentCourseMapper.selectOneStudentWithCourse(courseId,studentId);
    }

    @Override
    public void update(StudentCourse studentCourse) {
        studentCourseMapper.update(studentCourse);
    }

    @Override
    public int save(StudentCourse studentCourse) {
        return studentCourseMapper.insertStudentCourse(studentCourse);
    }

    @Override
    public void remove(Integer id) {
        studentCourseMapper.deleteStudentCourse(id);
    }

    @Override
    public void updateScore(StudentCourse studentCourse) {
        studentCourseMapper.updateScore(studentCourse);
    }

    @Override
    public void removeChoseCourse(Integer id, int stuId) {
        studentCourseMapper.deleteChoseCourse(id,stuId);
    }
}
