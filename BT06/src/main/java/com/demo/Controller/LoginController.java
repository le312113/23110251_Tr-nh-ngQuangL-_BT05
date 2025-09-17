package com.demo.Controller;

import com.demo.Entity.User;
import com.demo.Service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImplement userServiceImplement;
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // file resources/templates/login.html
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password) {
        User user= userServiceImplement.findByUsername(username);
        if (user!=null && user.getPassWord().equals(password)) {
            return waiting(user.getId());
        }
        return "login";
    }
    public String waiting(int id){
        switch (id){
            case 1:
                return "admin/home";
                case 2:
                    return "manager/home";
                    default:
                        return "user/home";
        }
    }
}

