package com.app.store.entity;

import com.app.store.utils.enums.PaymentStatus;
import com.app.store.utils.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.EnumType.STRING;

@Entity(name = "Payment_details")
@Data
public class PaymentDetails extends BaseEntity{

    @ManyToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(columnDefinition = "customer_id", referencedColumnName = "id")
    private User customerId;

    @Enumerated(STRING)
    private PaymentType paymentType;

    @Enumerated(STRING)
    private PaymentStatus status;

    @OneToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(columnDefinition = "order_id", referencedColumnName = "id")
    private Order orderID;

}
