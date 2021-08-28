package com.xzy.service.impl;


import com.xzy.mapper.CourseMapper;
import com.xzy.mapper.TeacherMapper;
import com.xzy.domain.*;
import com.xzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public void updateById(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public boolean removeById(Integer id) {
        return teacherMapper.deleteTeacherById(id);
    }

    @Override
    public List<Teacher> findByPaging(Integer toPageNo) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Teacher> list = teacherMapper.selectTeacherByPaging(pagingVO);

        return list;
    }

    @Override
    public int save(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int getCountTeacher() {
        return teacherMapper.getCountTeacher();
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.selectTeacherById(id);
    }

    @Override
    public List<Teacher> findByName(String name) {
        return teacherMapper.selectTeacherByName(name);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.selectTeacherAll();
    }
}
