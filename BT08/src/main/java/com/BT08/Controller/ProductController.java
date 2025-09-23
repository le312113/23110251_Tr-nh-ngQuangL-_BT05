package com.BT08.Controller;

import com.BT08.Entity.Product;
import com.BT08.Service.CategoryService;
import com.BT08.Service.IStorageService;
import com.BT08.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IStorageService storageService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        if (product.getCategory() != null) {
            categoryService.findById(product.getCategory().getCateId())
                    .ifPresent(product::setCategory);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product newProduct) {
        return productService.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setQuantity(newProduct.getQuantity());
            product.setUnitPrice(newProduct.getUnitPrice());
            product.setDescription(newProduct.getDescription());
            product.setDiscount(newProduct.getDiscount());
            product.setStatus(newProduct.getStatus());
            if (newProduct.getCategory() != null) {
                categoryService.findById(newProduct.getCategory().getCateId())
                        .ifPresent(product::setCategory);
            }
            return ResponseEntity.ok(productService.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
