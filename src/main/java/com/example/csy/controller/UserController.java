package com.example.csy.controller;

import com.example.csy.entity.User;
import com.example.csy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户级别：
     * 查询所有用户信息
     * 用于信息显示页面
     * @return
     */
    @RequestMapping("/user/queryAll")
    @ResponseBody
    public List<User> queryAll() {
        List<User> users = userService.queryAll();
        System.out.println("查询所有");
        return users;
    }

    /**
     * 用户级别：
     * 根据username查询，返回User对象
     * 用于登录操作
     * @param username
     * @return
     */
    @RequestMapping("/user/queryByName")
    @ResponseBody
    public User queryOneByName(String username){
        System.out.println("查询单个"+username);
        User user = userService.queryUserByUsername(username);
        return user;
    }

    /**
     * 管理员级别：
     * 增加用户
     * @param user
     * @return
     */
    @RequestMapping("/user/admin/add")
    @ResponseBody
    public int AddUser(@RequestBody User user) {
        System.out.println(user);
        int i = userService.addUser(user);
        System.out.println(i);
        return i;
    }

    /**
     * 管理员级别：
     * 删除用户
     * @param username
     * @return
     */
    @RequestMapping("/user/admin/delete")
    @ResponseBody
    public int deleteUser(@RequestBody String username){
        int i = userService.deleteUser(username.substring(0,username.length()-1));
        return i;
    }

    /**
     * 用户级别：
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user/admin/update")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        System.out.println(user);
        userService.updateUser(user);
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request) {
        // 由于是根据name参数获取的，我这里封装了一下
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 创建出一个 Token 内容本质基于前台的用户名和密码（不一定正确）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取 subject 认证主体（这里也就是现在登录的用户）
        Subject subject = SecurityUtils.getSubject();
        try{
            // 认证开始，这里会跳转到自定义的 UserRealm 中
            subject.login(token);
            // 可以存储到 session 中
            request.getSession().setAttribute("user", user);
            return "views/success";
        }catch(Exception e){
            // 捕获异常
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("errorMsg", "兄弟，用户名或密码错误");
            return "views/login";
        }
    }
}
