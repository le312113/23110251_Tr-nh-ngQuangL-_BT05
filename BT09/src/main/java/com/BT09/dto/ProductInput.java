package com.BT09.dto;


import java.math.BigDecimal;

public record ProductInput(Long id, String title, Integer quantity, String desc, BigDecimal price, Long categoryId) {}
