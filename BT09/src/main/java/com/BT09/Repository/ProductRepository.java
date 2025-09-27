package com.BT09.Repository;

import com.BT09.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> findAllByOrderByPriceAsc();

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.price ASC")
    List<Product> findByCategoryId(@Param("categoryId") int categoryId);
}
