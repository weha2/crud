package com.weha.crud.repository;

import com.weha.crud.entity.AddressEntity;
import com.weha.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<List<AddressEntity>> findByUser(UserEntity user);
}
