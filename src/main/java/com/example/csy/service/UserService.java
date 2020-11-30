package com.example.csy.service;

import com.example.csy.entity.Permissions;
import com.example.csy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> queryAll();

    User queryOneUserByName(String username);

    User queryUserByUsername(String username);

    Permissions queryPermissionByUsername(String username);

    int addUser(User user);

    int deleteUser(String username);

    void updateUser(User user);
}
