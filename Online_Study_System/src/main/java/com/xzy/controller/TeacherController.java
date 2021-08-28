package com.xzy.controller;

import com.xzy.exception.CustomException;
import com.xzy.domain.*;
import com.xzy.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;



@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * 获取当前用户名
     * @return 用户名
     */
    private String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userName;
    }

    /**
     * 获取当前登陆用户名的id
     * @return
     */
    private Integer getUserId() throws Exception{
        User user = userService.findByName(getUserName());
        return user.getId();
    }

    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/logout";
    }

    /**
     * 个人中心页面
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showSelf")
    public String showSelf (Model model) throws Exception {
        Teacher teacher = teacherService.findById(getUserId());

        model.addAttribute("teacher", teacher);
        return "teacher/showSelf";
    }

    /**
     * 显示我的课程页面
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {

        List<Course> list = courseService.findByTeacherID(getUserId());
        model.addAttribute("courseList", list);
        return "teacher/showCourse";
    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String editCourseUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/teacher/showCourse";
        }
        Course course = courseService.findById(id);
        if (course == null) {
            throw new CustomException("未找到该课程");
        }
        Integer teacherId = getUserId();
        Teacher teacher = teacherService.findById(teacherId);
        model.addAttribute("teacher", teacher);
        model.addAttribute("course", course);

        return "teacher/editCourse";
    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String saveCollegeVideo(@RequestParam("file") MultipartFile multipartFile, Course course, HttpSession session, HttpServletRequest request) throws Exception {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String fileDir = "D://graduate/Online_Study_System/src/main/webapp/video/" + fileName;
            multipartFile.transferTo(new File(fileDir));
            course.setVideo(fileName);
            course.setVideoUrl(fileDir);
            courseService.updateById(course);
            return "redirect:/teacher/showCourse";
        }catch (Exception e){
            throw new CustomException("文件上传失败");
        }
    }

    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        if (id == null) {
            return "teacher/showCourse";
        }
        boolean flag = courseService.removeById(id);
        //删除失败，说明selectCourse表中存在关联数据,先删除关联信息
        while(flag == false) {
            List<StudentCourse> lists = studentCourseService.findByCourseID(id);
            for (int i=0;i<lists.size();i++) {
                studentCourseService.remove(id);
            }
            flag = courseService.removeById(id);
        }
        /*String fileName = multipartFile.getOriginalFilename();
        String fileDir = "D://graduate/Online_Study_System/src/main/webapp/video/" + fileName;
        File file = new File(fileDir);
        file.delete();*/
        return "redirect:/teacher/showCourse";
    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String addCourseUI(Model model) throws Exception {
        Integer teacherId = getUserId();
        Teacher teacher = teacherService.findById(teacherId);
        model.addAttribute("teacher", teacher);
        return "teacher/addCourse";
    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String add( @RequestParam("file") MultipartFile multipartFile, Course course)throws Exception{
        try {
            String fileName = multipartFile.getOriginalFilename();
            String fileDir = "D://graduate/Online_Study_System/src/main/webapp/video/" + fileName;
            multipartFile.transferTo(new File(fileDir));
            course.setVideo(fileName);
            course.setVideoUrl(fileDir);
            courseService.save(course);
            return "redirect:/teacher/showCourse";
        }catch (Exception e){
            throw new CustomException("文件上传失败");
        }
    }


    /**
     * 查看学习该课程的学生信息
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showStudent")
    public String gradeCourse( Model model) throws Exception {
        int  teacherId=getUserId();
        List<Course> courseList = courseService.findByTeacherID(teacherId);
        List<StudentCourse> list=null;
        for (int i = 0; i < courseList.size(); i++) {
            list = studentCourseService.findByCourseID(courseList.get(i).getId());
        }

        model.addAttribute("StudentCourseList", list);
        model.addAttribute("courseList",courseList);
        return "teacher/showStudent";
    }

    /**
     * 修改密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

}
