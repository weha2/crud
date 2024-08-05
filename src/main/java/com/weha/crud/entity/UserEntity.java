package com.weha.crud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    @JsonIgnore
    private String firstName;

    @Column
    private String lastName;

}
