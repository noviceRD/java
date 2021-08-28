package com.xzy.mapper;

import com.xzy.domain.PagingVO;
import com.xzy.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    void updateTeacher(Teacher teacher);

    boolean deleteTeacherById(Integer id);

    List<Teacher> selectTeacherByPaging(PagingVO pagingVO);

    int insertTeacher(Teacher teacher);

    int getCountTeacher();

    Teacher selectTeacherById(Integer id);

    List<Teacher> selectTeacherByName(String name);

    List<Teacher> selectTeacherAll();

}