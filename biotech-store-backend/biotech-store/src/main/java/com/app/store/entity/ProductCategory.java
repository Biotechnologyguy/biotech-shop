package com.app.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "product_category")
@Data
public class ProductCategory extends BaseEntity{

    @Column(name = "category_name", length = 45, unique = true)
    @NotBlank(message = "Category name is required")
    private String categoryName;

    @Column(name = "description", length = 1000)
    @NotBlank(message = "Description is required")
    private String description;

}
