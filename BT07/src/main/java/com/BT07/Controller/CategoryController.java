package com.BT07.Controller;

import com.BT07.Entity.Category;
import com.BT07.Service.CategoryServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
@Controller
public class CategoryController {
    @Autowired
    private CategoryServiceImplement categoryService;
    @GetMapping("/category")
    public String CategoryPage(Model model,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;
        Page<Category> categoryPage = categoryService.findCategoryByName(keyword, page, pageSize);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageTitle", "Category List");
        return "category/list";
    }
    @GetMapping("/category/add")
    public String addPage(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Thêm Category");
        return "category/form";
    }
    @GetMapping("/category/edit")
    public String editPage(@RequestParam int id, Model model){
        model.addAttribute("category",categoryService.findCategoryById(id));
        model.addAttribute("pageTitle", "Chỉnh sửa Category");
        return "category/form";
    }
    @PostMapping("/category/add")
    public String saveCategory(@ModelAttribute Category category){
        categoryService.saveCategory(category);
        return "redirect:/category";
    }
    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam int id){
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
