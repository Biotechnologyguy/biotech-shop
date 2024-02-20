package com.app.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Data
@Entity(name = "address")
public class Address extends BaseEntity{

    @ManyToOne(cascade = ALL, fetch = EAGER)
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private User user;

    @Column(name = "address_line1", length = 100)
    @NotBlank(message = "Address line 1 is required")
    private String addressLine1;

    @Column(name = "address_line2", length = 100)
    private String addressLine2;

    @Column(name = "city", length = 45)
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "state", length = 45)
    @NotBlank(message = "State is required")
    private String state;

    @Column(name = "country", length = 45)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "postal_code", length = 10)
    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    @Column(name = "is_primary", columnDefinition = "boolean default false")
    private Boolean isPrimary;


}

