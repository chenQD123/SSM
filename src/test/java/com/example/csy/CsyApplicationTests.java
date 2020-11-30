package com.example.csy;

import com.example.csy.dao.UserMapper;
import com.example.csy.entity.Permissions;
import com.example.csy.entity.User;
import com.example.csy.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.example.csy.dao")
class CsyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void queryAll(){
        List<User> users = userService.queryAll();
        System.out.println(users);
    }

    @Test
    void contextLoads() {
        User admin = userService.queryUserByUsername("admin");
        System.out.println(admin.toString());
        Permissions permission = userService.queryPermissionByUsername("admin");
        System.out.println(permission.toString());
    }

    @Test
    void addTest() {
        User user = new User("wu","123",3);
        int i = userService.addUser(user);
        System.out.println(i);
    }

    @Test
    void deleteTest() {
        userService.deleteUser("zhangsan");
    }

    @Test
    void updateTest() {
        User user = new User("ww","123",3);
    }
}
