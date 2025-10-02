package com.BT10.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    @Column(name="product_name")
    private String productName;
    private int quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private String images;
    private String description;
    private BigDecimal discount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date creationDate = new Date();
    private int status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cate_id",referencedColumnName = "cate_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Category category;
}
