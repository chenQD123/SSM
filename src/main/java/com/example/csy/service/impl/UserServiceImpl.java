package com.example.csy.service.impl;

import com.example.csy.dao.UserMapper;
import com.example.csy.entity.Permissions;
import com.example.csy.entity.User;
import com.example.csy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户级别：
     * 查询所有用户信息
     * 用于信息显示页面
     * @return
     */
    @Override
    public List<User> queryAll() {
        return userMapper.queryAllUser();
    }

    /**
     * 查询一个用户，但是返回类型只有User，没有嵌套结果，和queryUserByUsername不一样
     * @Param username
     * @return
     */
    @Override
    public User queryOneUserByName(String username) {
        return userMapper.queryOneUserByName(username);
    }

    /**
     * 用户级别：
     * 根据username查询，返回User对象
     * 用于登录操作
     * @param username
     * @return
     */
    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    /**
     * 用户级别：
     * 根据username查询，返回Permissions
     * 用于权限查询
     * @param username
     * @return
     */
    @Override
    public Permissions queryPermissionByUsername(String username) {
        return userMapper.queryPermissionByUsername(username);
    }

    /**
     * 管理员级别：
     * 增加用户
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        if (userMapper.queryUserByUsername(user.getUsername()) == null){
            int i = userMapper.addUser(user);
            return i;
        }
        System.out.println("添加失败！该用户已存在");
        return 0;
    }

    /**
     * 管理员级别：
     * 删除用户
     * @param username
     * @return
     */
    @Override
    public int deleteUser(String username) {
        if (userMapper.queryUserByUsername(username) != null){
            int i = userMapper.deleteUser(username);
            return i;
        }
        System.out.println("删除失败！该用户不存在");
        return 0;
    }

    /**
     * 用户级别：
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
        System.out.println("修改成功！");
    }
}
