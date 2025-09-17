package com.demo.Service;

import com.demo.Entity.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    void editUser(User user);
    void deleteUser(int id);
    List<User> listAll();
    User findByUsername(String username);
    User findById(int id);
}
