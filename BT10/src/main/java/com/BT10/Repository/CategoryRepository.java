package com.BT10.Repository;
import com.BT10.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCateId(Long id);
    Page<Category> findByCateNameContainingIgnoreCase(String keyword, Pageable pageable);
}
