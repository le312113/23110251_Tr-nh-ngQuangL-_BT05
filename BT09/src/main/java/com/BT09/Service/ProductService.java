package com.BT09.Service;

import com.BT09.Entity.Category;
import com.BT09.Entity.Product;
import com.BT09.Repository.ProductRepository;
import com.BT09.dto.ProductInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public List<Product> findAllOrderByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }
    public List<Product> findByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }
    public Product upsert(ProductInput input) {
        Product product = new Product();
        if (input.id() != null) {
            product.setId(Math.toIntExact(input.id()));
        }
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setDescription(input.desc());
        product.setPrice(input.price());
        if (input.categoryId() != null) {
            Category category = categoryService.findById(Math.toIntExact(input.categoryId()));
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    // Xóa sản phẩm theo ID
    public boolean delete(int id) {
        productRepository.deleteById(id);
        return true;
    }
}
