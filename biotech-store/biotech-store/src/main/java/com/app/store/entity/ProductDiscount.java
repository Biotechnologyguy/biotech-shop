package com.app.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "product_discount")
@Data
public class ProductDiscount extends BaseEntity {

    @Column(name = "name", length = 45)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "description", length = 100)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(name = "discount_percentage")
    @NotBlank(message = "Discount percentage is required")
    private Double discountPercentage;

    @Column(name = "is_active")
    private Boolean isActive;

}
