package com.BT11.Controller;
import com.BT11.Entity.UserInfo;
import com.BT11.Service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        System.out.println("=== ADD USER CALLED ===");
        System.out.println("Name: " + userInfo.getName());
        System.out.println("Email: " + userInfo.getEmail());
        System.out.println("Password (raw): " + userInfo.getPassword());
        System.out.println("Roles: " + userInfo.getRoles());
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        System.out.println("Password (encoded): " + userInfo.getPassword());

        userService.addUser(userInfo);

        System.out.println("=== USER SAVED ===");
        return "Thêm user thành công!";
    }
}
