package com.BT09.Service;

import com.BT09.Entity.User;
import com.BT09.Repository.UserRepository;
import com.BT09.dto.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User upsert(UserInput input){
        User user = new User();
        if (input.id() != null) {
            user = userRepository.findById(Math.toIntExact(input.id())).orElse(new User());
        }
        user.setName(input.name());
        user.setEmail(input.email());
        user.setPhone(input.phone());
        user.setPassword(input.password());
        return userRepository.save(user);
    }
    public boolean delete(int id){
        userRepository.deleteById(id);
        return true;
    }
    public User findById(int id){
        return userRepository.findById(id).orElse(new User());
    }
}
