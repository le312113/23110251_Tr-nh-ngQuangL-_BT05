package com.demo.Service;

import com.demo.Entity.Category;
import com.demo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public void insertCategory(Category category){
        categoryRepository.save(category);
    }
    public void editCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(int cate_id){
        categoryRepository.deleteById(cate_id);
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Category findById(int id){
        return categoryRepository.findById(id);
    }
}
