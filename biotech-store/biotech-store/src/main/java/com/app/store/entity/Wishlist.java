package com.app.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "wishlist")
@Data
public class Wishlist extends BaseEntity{

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customerId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

}
