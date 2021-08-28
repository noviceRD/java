package com.xzy.realm;

import com.xzy.utils.SHA1Utils;
import com.xzy.domain.Role;
import com.xzy.domain.User;
import com.xzy.service.RoleService;
import com.xzy.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


@Component
public class LoginRealm extends AuthorizingRealm{

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 获取身份信息，从数据库获取该用户的权限和角色信息，当调用权限验证时自动调用。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            User user = userService.findByName(name);
            //获取角色对象
            role = roleService.findByid(user.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getName());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 进行身份验证，登陆时自动调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        String password = new String((char[])token.getCredentials());

        User user = null;
        try {
            user = userService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user == null) {
            //没有该用户名
            throw new UnknownAccountException("该用户不存在！");
        } else if (!SHA1Utils.validatePassword(password, user.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException("密码错误！");
        }

        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username,password,getName());

        return aInfo;
    }
}
