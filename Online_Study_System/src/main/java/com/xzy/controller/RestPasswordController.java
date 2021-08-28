package com.xzy.controller;

import com.xzy.utils.SHA1Utils;
import com.xzy.exception.CustomException;
import com.xzy.domain.User;
import com.xzy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;



@Controller
public class RestPasswordController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 重置当前账户密码
     * @param oldPassword
     * @param password1
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        User user = userService.findByName(username);

        if (!SHA1Utils.validatePassword(oldPassword,user.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            user.setPassword(SHA1Utils.entryptPassword(password1));
            userService.updateByName(username, user);
        }

        return "redirect:/logout";
    }

}
