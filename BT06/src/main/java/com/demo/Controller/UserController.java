package com.demo.Controller;
import com.demo.Entity.User;
import com.demo.Service.UserServiceImplement;
import org.springframework.ui.Model;
import com.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserServiceImplement userService;
    @GetMapping("/admin/user/add")
    public String addPage(Model model){
        model.addAttribute("user", new User());
        return "admin/user/add_user";
    }
    @GetMapping("/admin/user/edit")
    public String editPage(Model model,@RequestParam int id ){
        User user=userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/edit_user";
    }
    @PostMapping("/admin/user/add")
    public String addUser(@ModelAttribute("user") User user){
        user.setId(0);
        userService.insertUser(user);
        return "redirect:/admin/user";
    }
    @PostMapping("admin/user/edit")
    public String editUser(@ModelAttribute("user")  User user){
        userService.editUser(user);
        return "redirect:/admin/user";
    }
    @PostMapping("admin/user/delete")
    public String deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
