package com.weha.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(nullable = true)
    private LocalDateTime modifierDate;

    @Column
    private final LocalDateTime createdDate = LocalDateTime.now();
}
