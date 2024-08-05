package com.weha.crud.repository;

import com.weha.crud.entity.SocialEntity;
import com.weha.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<SocialEntity, Long> {
    Optional<SocialEntity> findByUser(UserEntity user);
}
