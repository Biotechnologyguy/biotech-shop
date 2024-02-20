package com.app.store.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "cart")
@Data
public class Cart extends BaseEntity{

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customerID;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productID;

    private Long quantity;
}
