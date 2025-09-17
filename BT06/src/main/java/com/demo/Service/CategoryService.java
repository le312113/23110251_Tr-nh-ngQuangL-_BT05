package com.demo.Service;

import com.demo.Entity.Category;

import java.util.List;

public interface CategoryService {
    void insertCategory(Category category);
    void editCategory(Category category);
    void deleteCategory(int cate_id);
    List<Category> getAllCategory();
    Category findById(int id);
}
