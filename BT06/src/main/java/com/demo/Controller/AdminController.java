package com.demo.Controller;
import com.demo.Service.CategoryServiceImplement;
import com.demo.Service.UserServiceImplement;
import com.demo.Service.VideoServiceImplement;
import org.springframework.ui.Model;
import com.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private UserServiceImplement userService;
    @Autowired
    private CategoryServiceImplement categoryService;
    @Autowired
    private VideoServiceImplement videoService;
    @GetMapping( "admin/home")
    public String homePage(){
        return "admin/home";
    }
    @GetMapping("admin/user")
    public String userPage(Model model){
        model.addAttribute("users", userService.listAll());
        return "admin/user/list_user";
    }
    @GetMapping("admin/category")
    public String categoryPage(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/category/list_category";
    }
    @GetMapping("admin/video")
    public String videoPage(Model model){
        model.addAttribute("videos",videoService.getAllVideo());
        return "admin/video/list_video";
    }
}
