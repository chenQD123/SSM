package com.example.csy.dao;

import com.example.csy.entity.Permissions;
import com.example.csy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 用户级别：
     * 查询所有用户信息
     * 用于信息显示页面
     * @return
     */
    @Select("select * from user")
    List<User> queryAllUser();

    /**
     * 查询一个用户，但是返回类型只有User，没有嵌套结果，和queryUserByUsername不一样
     * @return
     */
    @Select("select * from user where username=#{username}")
    User queryOneUserByName(String username);

    /**
     * 用户级别：
     * 根据username查询，返回User对象
     * 用于登录操作
     * @param username
     * @return
     */
//    @Select("SELECT u.*,r.role_name FROM `user` u, `role` r" +
//            "WHERE username = #{username} AND u.role_id = r.id")
    User queryUserByUsername(@Param("username") String username);



    /**
     * 用户级别：
     * 根据username查询，返回Permissions
     * 用于权限查询
     * @param username
     * @return
     */
//    @Select("SELECT p.* ,r.role_name FROM `user` u, `role` r, `permission` p" +
//            "WHERE username = #{username} AND u.role_id = r.id AND p.role_id = r.id;")
    Permissions queryPermissionByUsername(@Param("username") String username);

    /**
     * 管理员级别：
     * 增加用户
     * @param user
     * @return
     */
    @Insert("insert into user(username,password,role_id) " +
            "values(#{username},#{password},#{roleId})")
    int addUser(User user);

    /**
     * 管理员级别：
     * 删除用户
     * @param username
     * @return
     */
    @Delete("delete from user where username=#{username}")
    int deleteUser(@Param("username") String username);

    /**
     * 用户级别：
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set username=#{username},password=#{password},role_id=#{roleId} where id=#{id}")
    void updateUser(User user);
}

