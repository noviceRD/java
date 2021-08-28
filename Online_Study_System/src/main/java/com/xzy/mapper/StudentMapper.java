package com.xzy.mapper;

import com.xzy.domain.PagingVO;
import com.xzy.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    void updateStudent(Student student);

    Boolean deleteStudentById(Integer id);

    List<Student> selectStudentByPaging(PagingVO pagingVO);

    int insertStudent(Student student);

    int getCountStudent();

    Student selectStudentById(Integer id);

    List<Student> selectStudentByName(String name);
}