package com.xzy.controller;

import com.xzy.utils.GlobalConstant;
import com.xzy.utils.SHA1Utils;
import com.xzy.exception.CustomException;
import com.xzy.domain.*;
import com.xzy.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private UserService userService;




    /* ----- 课程管理区 START ----- */

    @RequestMapping("/showCourse")
    public String showCourse(Model model, Integer page) throws Exception {

        List<Course> courseList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCourse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            courseList = courseService.findByPaging(1);

        } else {
            pagingVO.setToPageNo(page);
            courseList = courseService.findByPaging(page);

        }
        model.addAttribute("courseList", courseList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showCourse";

    }



    /* ----- 课程管理区 END ----- */


    /* ----- 学生管理区 START ----- */

    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {
        List<Student> studentList ;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.getCountStudent());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
             studentList = studentService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            studentList = studentService.findByPaging(page);
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showStudent";

    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String addStudentUI() throws Exception {
        return "admin/addStudent";
    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(Student student) throws Exception {
        User user =null;
        if(user != null) {
            throw new CustomException("该名称已被注册,无法添加!");
        } else {
            user = new User();
            user.setName(student.getName());
            user.setPassword(SHA1Utils.entryptPassword(GlobalConstant.DEFAULT_PASSWD));
            user.setRole(GlobalConstant.ROle_Type.STUDENT.getIndex());
            userService.save(user);

            student.setId(user.getId());
            studentService.save(student);
        }

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String editStudentUI(Integer id, Model model) throws Exception {
        Student student = null;

        student = studentService.findById(id);
        if(student == null) {
            throw new CustomException("该用户不存在!");
        }

        model.addAttribute("student", student);

        return "admin/editStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(Student student) throws Exception {

        User user = userService.findById(student.getId());
        user.setName(student.getName());
        userService.updateById(student.getId(),user);

        studentService.updateById(student);

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    public String removeStudent(Integer id) throws Exception {
        boolean flag = studentService.removeById(id);
        //flag false 表示该学生有课程,递归删除该学生课程
        while(flag == false){
            List<StudentCourse> lists = studentCourseService.findByStudentID(id);
            for (int i = 0; i <lists.size() ; i++) {
                studentCourseService.remove(id);
            }
            flag = studentService.removeById(id);
        }

        userService.removeById(id);

        return "redirect:/admin/showStudent";
    }

    /* ----- 学生管理区 END ----- */


    /* ----- 教师管理区 START ----- */

    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {

        List<Teacher> teacherList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(teacherService.getCountTeacher());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            teacherList = teacherService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            teacherList = teacherService.findByPaging(page);
        }
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTeacher";

    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI() throws Exception {

        return "admin/addTeacher";
    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher) throws Exception {
        User user = null;
        user = userService.findByName(teacher.getName());
        if(user != null) {
            throw new CustomException("该名称已被注册,无法注册!");
        } else {
            user = new User();
            user.setName(teacher.getName());
            user.setPassword(SHA1Utils.entryptPassword(GlobalConstant.DEFAULT_PASSWD));
            user.setRole(GlobalConstant.ROle_Type.TEACHER.getIndex());
            userService.save(user);

            teacher.setId(user.getId());
            teacherService.save(teacher);

        }
        return "redirect:/admin/showTeacher";
    }

    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            throw new CustomException("未找到该教师");
        }
        model.addAttribute("teacher", teacher);

        return "admin/editTeacher";
    }

    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(Teacher teacher) throws Exception {
        teacherService.updateById(teacher);

        return "redirect:/admin/showTeacher";
    }

    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        boolean flag = teacherService.removeById(id);
        if(flag == false) {
            throw new CustomException("该老师存在相应课程,无法删除");
        }
        userService.removeById(id);
        return "redirect:/admin/showTeacher";
    }


    /* ----- 教师管理区 END ----- */


    /* ----- 其他区 START ----- */

    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/logout";
    }

    /**
     * 重置当前账户密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/passwordRest")
    public String passwordRestUI()  {
        return "admin/passwordRest";
    }

    /* ----- 其他区 END ----- */
}
