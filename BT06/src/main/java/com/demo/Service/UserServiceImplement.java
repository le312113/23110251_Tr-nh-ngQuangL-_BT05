package com.demo.Service;

import com.demo.Entity.User;
import com.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImplement implements UserService{
    @Autowired
    private UserRepository userRepository;
    public void insertUser(User user){
        user.setCreatedDate(new Date());
        user.setRoleid(2);
        userRepository.save(user);
    }
    public void editUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public List<User> listAll(){
        return userRepository.findAll();
    }
    public User findByUsername(String username){
        return userRepository.findByUserName(username);
    }
    public User findById(int id){
        return  userRepository.findById(id);
    }
}
