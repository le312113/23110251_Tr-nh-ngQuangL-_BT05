package com.BT07.Service;

import com.BT07.Entity.Category;
import com.BT07.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
    public Category findCategoryById(int id){
        return categoryRepository.findById(id);
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Page<Category> findCategoryByName(String keyword, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return categoryRepository.findByCateNameContainingIgnoreCase(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }
}
