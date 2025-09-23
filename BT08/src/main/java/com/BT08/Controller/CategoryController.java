package com.BT08.Controller;

import com.BT08.Entity.Category;
import com.BT08.Service.CategoryService;
import com.BT08.Service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IStorageService storageService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Category> create(
            @RequestParam("cateName") String cateName,
            @RequestParam(value = "icon", required = false) MultipartFile icon) {
        Category category = new Category();
        category.setCateName(cateName);

        if (icon != null && !icon.isEmpty()) {
            String fileName = icon.getOriginalFilename();
            category.setIcon(fileName);
            // TODO: gọi storageService.store(icon, fileName) để lưu file thật
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }


    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Category> update(
            @PathVariable Integer id,
            @RequestParam("cateName") String cateName,
            @RequestParam(value = "icon", required = false) MultipartFile icon) {

        return categoryService.findById(id).map(category -> {
            category.setCateName(cateName);
            if (icon != null && !icon.isEmpty()) {
                String fileName = icon.getOriginalFilename();
                storageService.store(icon, fileName);
                category.setIcon(fileName);
            }

            return ResponseEntity.ok(categoryService.save(category));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

