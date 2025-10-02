package com.BT10.Controller;

import com.BT10.Dto.LoginDTO;
import com.BT10.Entity.User;
import com.BT10.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String homePage(Model model) {
        model.addAttribute("loginRequest", new LoginDTO());
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginDTO loginRequest, BindingResult result, Model model, HttpSession session) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());
        if (result.hasErrors()) {
            return "login";
        }

        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            System.out.println("User is logged in");
            session.setAttribute("role", user.getRoleid());
            if (user.getRoleid() == 1) {
                return "redirect:/admin/home";
            }
            return "user/home";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }
}
