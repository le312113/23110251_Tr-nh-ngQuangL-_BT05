package com.BT10.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")  // ánh xạ đúng tên cột trong DB
    private Long cateId; // Đảm bảo sử dụng đúng tên thuộc tính trong Java

    @Column(name = "cate_name")
    private String cateName;

    @Column(name = "icons")
    private String icons;
}

