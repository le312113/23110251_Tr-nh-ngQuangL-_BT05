package com.demo.Controller;

import com.demo.Entity.Category;
import com.demo.Service.CategoryService;
import com.demo.Service.CategoryServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    private CategoryServiceImplement categoryService;
    @GetMapping("/admin/category/add")
    public String addPage(Model model){
        model.addAttribute("category", new Category());
        return "admin/category/add_category";
    }
    @GetMapping("/admin/category/edit")
    public String editPage(Model model,@RequestParam int cate_id ){
        Category category=categoryService.findById(cate_id);
        model.addAttribute("category",category);
        return  "admin/category/edit_category";
    }
    @PostMapping("/admin/category/edit")
    public String editCategory(@ModelAttribute("category") Category category){
        categoryService.editCategory(category);
        return "redirect:/admin/category";
    }
    @PostMapping("/admin/category/add")
    public String addCategory(@ModelAttribute("category") Category category){
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }
    @PostMapping("/admin/category/delete")
    public String deleteCategory(@RequestParam int cate_id){
        categoryService.deleteCategory(cate_id);
        return "redirect:/admin/category";
    }
}
