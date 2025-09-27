// Product.java
package com.BT09.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "Product", schema = "dbo")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int quantity;

    @Column(name = "description")
    private String description;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id", referencedColumnName = "id")
    private Category category;
}
