package com.BT10.Controller;

import com.BT10.Entity.Category;
import com.BT10.Entity.Product;
import com.BT10.Service.CategoryService;
import com.BT10.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("/admin/home")  // Add leading slash for correct path mapping
    public String adminPage(Model model,
                            @RequestParam(defaultValue = "") String keyword,
                            @RequestParam(defaultValue = "0") int page) {

        int pageSize = 2;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> categoryPage = categoryService.findCategoryByName(keyword, pageable);
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
    @GetMapping("/admin/product")
    public String productList(@RequestParam(defaultValue = "") String keyword,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productService.findProductsByKeyword(keyword, pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("product", new Product());
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "admin/product";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam(defaultValue = "") String keyword,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        // Load danh sách sản phẩm cho table
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productService.findProductsByKeyword(keyword, pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);

        // Load product cần edit (với category đã được fetch)
        Product product = productService.findById(id);
        model.addAttribute("product", product);

        // Load categories cho dropdown
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "admin/product";
    }

    // Lưu sản phẩm (thêm mới hoặc cập nhật)
    @PostMapping("/admin/product/save")
    public String saveProduct(@ModelAttribute Product product) {
        if (product.getProductId() > 0) {
            // Đang edit - giữ nguyên creation date
            Product existingProduct = productService.findById(product.getProductId());
            product.setCreationDate(existingProduct.getCreationDate());
        } else {
            // Đang thêm mới - set creation date
            product.setCreationDate(new Date());
        }
        productService.save(product);
        return "redirect:/admin/product";
    }

    // Xóa sản phẩm
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/product";
    }
}
