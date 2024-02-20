package com.app.store.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "product_inventory")
@Data
public class ProductInventory extends BaseEntity{

    @NotBlank(message = "Product quantity is required")
    private Long quantity;

}
