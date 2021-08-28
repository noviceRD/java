package com.xzy.controller;

import com.xzy.exception.CustomException;
import com.xzy.domain.*;
import com.xzy.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private QuestionAnswerService qas;

    /* ----- 普通方法区 START ----- */

    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    private String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    /**
     * 获取当前登陆用户名的id
     *
     * @return
     */
    private Integer getUserId() throws Exception {
        User user = userService.findByName(getUserName());
        return user.getId();
    }

    /* ----- 所有课程区 START ----- */

    /**
     * 显示所有课程
     *
     * @param model Model对象
     * @param page  页码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {

        List<Course> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCourse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
    }

    /**
     * 选课操作
     *
     * @param id 课程id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        int stuId = getUserId();
        StudentCourse s = studentCourseService.findOne(id, stuId);
        if(s!=null) {
            return "redirect:/student/selectedCourse";
        }else{
            //保存选课信息
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setCourseId(id);
            studentCourse.setStudentId(stuId);
            studentCourseService.save(studentCourse);
            return "redirect:/student/selectedCourse";
        }

    }

    /**
     * 退课操作
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        int stuId=getUserId();
        studentCourseService.removeChoseCourse(id,stuId);
        return "student/selectCourse";
    }

    /* ----- 所有课程区 END ----- */


    /**
     * 显示所有已选课程
     *
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        int stuId = getUserId();

        try {
            List<StudentCourse> list = studentCourseService.findByStudentID(stuId);


            model.addAttribute("StudentCourseList", list);

            return "student/selectCourse";
        } catch (Exception e) {
            throw new CustomException("未获取到已选课程！");
        }
    }

    /**
     * 观看视频
     *
     * @return
     */
    @RequestMapping(value = "/study", method = RequestMethod.GET)
    public String play(Integer id,Model model) {
        Course course = courseService.queryCourseById(id);
        model.addAttribute("course", course);

        return "student/study";
    }


    /**
     * 进入课后练习
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/startExam")
    public String startExam(@RequestParam int id, Model model, HttpServletRequest request)  {
        List<QuestionAnswer> questionsList=qas.queryQuestions(id);
        Course course = courseService.queryCourseById(id);
        model.addAttribute("questionsList", questionsList);
        model.addAttribute("course", course);
        return "/student/exam";
    }




    /**
     * 查看试题与答案考试信息
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveExam")
    public String saveExam(@RequestParam int id, @RequestParam String[] answerchoice, Model model) throws Exception {
        User user = userService.findByName(getUserName());
        Integer userId = user.getId();
        List<QuestionAnswer> questionsList=qas.queryQuestions(id);
        int stuId = getUserId();
        StudentCourse studentCourse = studentCourseService.findOne(id,stuId);
        //成绩
        int score=0;
        for(int i=0;i<questionsList.size();i++){
            questionsList.get(i).setMyAnswer(answerchoice[i]);
            if(questionsList.get(i).getAnswer().equals(answerchoice[i])) {
                score += questionsList.get(i).getScore();
            }
        }
        studentCourse.setCourseId(id);
        studentCourse.setStudentId(userId);
        //设置是否考过试为true
        studentCourse.setHasExam(1);
        studentCourse.setMark(score);
        System.out.println("成绩："+score);
        studentCourseService.updateScore(studentCourse);
        model.addAttribute("questionsList",questionsList);
        model.addAttribute("studentCourse",studentCourse);
        return "student/ExamAndAnswer";
    }


    /* ----- 已选课程区 END ----- */

    /* ----- 已修课程区 START ----- */
    /**
     * 显示所有已修课程
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {
        int stuId = getUserId();
        try{
            List<StudentCourse> studentCourseList = studentCourseService.findCourseByStudentID(stuId);
            List<StudentCourse> list=new ArrayList<StudentCourse>();
            for (int i = 0; i < studentCourseList.size(); i++) {
                if(studentCourseList.get(i).getHasExam().equals(1)){
                    list.add(studentCourseList.get(i));
                }
            }
            model.addAttribute("StudentCourseList", list);
        }
        catch(Exception e) {
                throw new CustomException("未获取到已修课程！");
        }
        return "student/overCourse";
    }

    /* ----- 已修课程区 END ----- */

    /* ----- 其他区 START ----- */
    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:/logout";
    }

    /**
     * 显示个人中心
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showSelf")
    public String showSelf(Model model) throws Exception {
        Student student = studentService.findById(getUserId());

        model.addAttribute("student", student);
        return "student/showSelf";
    }

    /**
     * 显示修改密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }
    /* ----- 其他区 END ----- */
}
