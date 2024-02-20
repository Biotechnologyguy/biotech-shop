package com.app.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "product")
@Data
public class Product extends BaseEntity{

    @ManyToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory productCategoryId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "product_inventory_id", referencedColumnName = "id")
    private ProductInventory productInventoryId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "product_discount_id", referencedColumnName = "id")
    private ProductDiscount productDiscountId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private User supplierId;

    @Column(name = "name", length = 45)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "description", length = 100)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(name = "price")
    @NotBlank(message = "Price is required")
    private Double price;

    @Column(name = "product_image", length = 100)
    private String productImage;

}
