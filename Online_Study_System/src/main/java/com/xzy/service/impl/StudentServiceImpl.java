package com.xzy.service.impl;


import com.xzy.mapper.StudentCourseMapper;
import com.xzy.mapper.StudentMapper;
import com.xzy.domain.*;
import com.xzy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;


    @Override
    public void updateById(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public Boolean removeById(Integer id) {
        //查询是否有有选课信息
        List<StudentCourse> list=studentCourseMapper.selectCourseWithStudent(id);
        if(list.size()==0){
            //没有则可以删除
            return studentMapper.deleteStudentById(id);
        }
        //有选择就不能删除
        return false;
    }

    @Override
    public List<Student> findByPaging(Integer toPageNo) {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Student> list = studentMapper.selectStudentByPaging(pagingVO);

        return list;
    }

    @Override
    public int save(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public int getCountStudent() {
        return studentMapper.getCountStudent();
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentMapper.selectStudentByName(name);
    }
}
