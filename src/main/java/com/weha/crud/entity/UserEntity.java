package com.weha.crud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    @JsonIgnore
    private String firstName;

    @Column
    private String lastName;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private SocialEntity social;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AddressEntity> addresses;

}
