package com.weha.crud.service;

import com.weha.crud.dtos.CreateUserDTO;
import com.weha.crud.dtos.ResponseUserDTO;
import com.weha.crud.entity.UserEntity;
import com.weha.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ResponseUserDTO> findUsers() {
        return userRepository
                .findAll()
                .stream().map(e -> new ResponseUserDTO(
                        e.getId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getCreatedDate()
                ))
                .toList();
    }

    public ResponseUserDTO createUser(CreateUserDTO req) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(req.fistName());
        entity.setLastName(req.lastName());
        return Optional.of(userRepository.save(entity))
                .map(e ->
                        new ResponseUserDTO(
                                e.getId(),
                                e.getFirstName(),
                                e.getLastName(),
                                e.getCreatedDate()
                        )
                ).orElse(null);
    }
}
