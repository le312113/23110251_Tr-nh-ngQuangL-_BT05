package com.BT08.Service;

import com.BT08.Entity.Category;
import com.BT08.Entity.Product;
import com.BT08.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
    public void deleteById(int cateId){
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}
