package com.BT10.Service;

import com.BT10.Entity.Category;
import com.BT10.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Page<Category> findCategoryByName(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return categoryRepository.findByCateNameContainingIgnoreCase(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
    public Category findCategoryById(Long id){
        return categoryRepository.findByCateId(id);
    }
}
