package com.BT10.Controller;

import com.BT10.Entity.Category;
import com.BT10.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/admin/home")  // Add leading slash for correct path mapping
    public String adminPage(Model model,
                            @RequestParam(defaultValue = "") String keyword,
                            @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> categoryPage = categoryService.findCategoryByName(keyword, pageable);

        // Add attributes for view rendering
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageTitle", "Category List");

        return "admin/home";
    }
    @PostMapping("/admin/category/delete")
    public String deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/home";
    }
    @GetMapping("/admin/category/add")
    public String addPage(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Thêm Category");
        return "admin/form";
    }
    @PostMapping("/admin/category/add")
    public String saveCategory(@ModelAttribute Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin/home";
    }
    @GetMapping("/admin/category/edit")
    public String editPage(@RequestParam Long id, Model model){
        model.addAttribute("category",categoryService.findCategoryById(id));
        model.addAttribute("pageTitle", "Chỉnh sửa Category");
        return "admin/form";
    }
}
