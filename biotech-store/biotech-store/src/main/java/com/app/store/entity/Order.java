package com.app.store.entity;

import com.app.store.utils.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.EnumType.STRING;

@Entity(name = "'order'")
@Data
public class Order extends BaseEntity{

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(columnDefinition = "customer_id", referencedColumnName = "id")
    private User customerId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(columnDefinition = "supplier_id", referencedColumnName = "id")
    private User supplierId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(columnDefinition = "address_id", referencedColumnName = "id")
    private Address addressId;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(columnDefinition = "payment_id", referencedColumnName = "id")
    private PaymentDetails paymentId;

    @Column(name = "status")
    @Enumerated(STRING)
    private OrderStatus status;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(columnDefinition = "product_id", referencedColumnName = "id")
    private Product productId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "amount")
    private Double amount;


}
