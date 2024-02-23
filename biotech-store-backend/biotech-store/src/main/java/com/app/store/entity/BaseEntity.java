package com.app.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDate.now;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Default
    @Column(name = "created_dt")
    private LocalDate createdDt=now();

    @Column(name = "created_by")
    private String createdBy;

    @Default
    @Column(name = "updated_dt")
    private LocalDate updatedDt=now();

    @Column(name = "updated_by")
    private String updatedBy;
}
