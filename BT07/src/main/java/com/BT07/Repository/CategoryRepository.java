package com.BT07.Repository;

import com.BT07.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int id);
    Page<Category> findByCateNameContainingIgnoreCase(String keyword, Pageable pageable);
}
