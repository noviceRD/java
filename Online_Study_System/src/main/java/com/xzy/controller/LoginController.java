package com.xzy.controller;

import com.xzy.utils.GlobalConstant;
import com.xzy.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    /**
     * 登录跳转
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }

    /**
     * 登录表单处理
     * @param user user对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(User user) throws Exception {

        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),
                user.getPassword());
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

        //所有用户均重定向对应的展示课程页面
        if (subject.hasRole(GlobalConstant.ROle_Type.ADMIN.getName())) {
            return "redirect:/admin/showCourse";
        } else if (subject.hasRole(GlobalConstant.ROle_Type.TEACHER.getName())) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole(GlobalConstant.ROle_Type.STUDENT.getName())) {
            return "redirect:/student/showCourse";
        }

        return "../../login";
    }

}
