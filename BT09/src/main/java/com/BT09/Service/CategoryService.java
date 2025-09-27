package com.BT09.Service;

import com.BT09.Entity.Category;
import com.BT09.Repository.CategoryRepository;
import com.BT09.dto.CategoryInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category findById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category upsert(CategoryInput input) {
        Category category = new Category();
        if (input.id() != null) {
            category = categoryRepository.findById(Math.toIntExact(input.id())).orElse(new Category());
        }
        category.setName(input.name());
        category.setImages(input.images());
        return categoryRepository.save(category);  // Lưu vào DB (insert/update)
    }
    public boolean delete(int id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
