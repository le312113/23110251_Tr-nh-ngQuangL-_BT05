package com.BT07.Service;

import com.BT07.Entity.Category;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface CategoryService {
    void saveCategory(Category category);
    void deleteCategory(int id);
    Category findCategoryById(int id);
    List<Category> getAllCategory();
    Page<Category> findCategoryByName(String keyword, int page, int size);
}
