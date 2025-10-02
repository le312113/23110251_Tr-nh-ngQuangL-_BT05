package com.BT10.Service;

import com.BT10.Entity.User;
import com.BT10.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User authenticate(String username,String password){
        User user= userRepository.findByUsername(username);
        if(user==null || !password.equals(user.getPassword())){
            return null;
        }
        return user;
    }
}
