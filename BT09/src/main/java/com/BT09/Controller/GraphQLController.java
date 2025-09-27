package com.BT09.Controller;

import com.BT09.Entity.Category;
import com.BT09.Entity.Product;
import com.BT09.Entity.User;
import com.BT09.Service.CategoryService;
import com.BT09.Service.ProductService;
import com.BT09.Service.UserService;
import com.BT09.dto.CategoryInput;
import com.BT09.dto.ProductInput;
import com.BT09.dto.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @QueryMapping
    public List<Product> products(@Argument Boolean orderByPriceAsc) {
        if (Boolean.TRUE.equals(orderByPriceAsc)) {
            return productService.findAllOrderByPriceAsc();
        }
        return productService.findAll();
    }

    // ===== Query: Lấy sản phẩm theo categoryId
    @QueryMapping
    public List<Product> productsByCategory(@Argument int categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    // ===== Query: Hiển thị tất cả các categories
    @QueryMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }

    // ===== Query: Hiển thị tất cả các users
    @QueryMapping
    public List<User> users() {
        return userService.findAll();
    }

    // ===== Mutation: Upsert Product
    @MutationMapping
    public Product upsertProduct(@Argument ProductInput input) {
        return productService.upsert(input);
    }

    // ===== Mutation: Xóa Product
    @MutationMapping
    public boolean deleteProduct(@Argument int id) {
        return productService.delete(id);
    }

    // ===== Mutation: Upsert Category
    @MutationMapping
    public Category upsertCategory(@Argument CategoryInput input) {
        return categoryService.upsert(input);
    }

    // ===== Mutation: Xóa Category
    @MutationMapping
    public boolean deleteCategory(@Argument int id) {
        return categoryService.delete(id);
    }

    // ===== Mutation: Upsert User
    @MutationMapping
    public User upsertUser(@Argument UserInput input) {
        return userService.upsert(input);
    }

    // ===== Mutation: Xóa User
    @MutationMapping
    public boolean deleteUser(@Argument int id) {
        return userService.delete(id);
    }
}
