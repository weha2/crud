package com.weha.crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id = 0L;

    @Column(nullable = true)
    @Setter
    private LocalDateTime modifierDate;

    @Column
    private final LocalDateTime createdDate = LocalDateTime.now();
}
