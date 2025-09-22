package com.BT08.Service;

import com.BT08.Entity.Product;
import com.BT08.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }
}
