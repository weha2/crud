package com.weha.crud.service;

import com.weha.crud.dtos.CreateUserDTO;
import com.weha.crud.dtos.ResponseUserDTO;
import com.weha.crud.entity.UserEntity;
import com.weha.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ResponseUserDTO findById(long id) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new Exception("Not found user");
        return userEntity
                .map(u -> new ResponseUserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getCreatedDate()
                ))
                .orElse(null);
    }

    public ResponseUserDTO createUser(CreateUserDTO req) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(req.firstName());
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

    public ResponseUserDTO updateUser(long id, CreateUserDTO req) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new Exception("Not found user");
        UserEntity user = userEntity.get();
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setModifierDate(LocalDateTime.now());
        return Optional.of(userRepository.save(user))
                .map(u -> new ResponseUserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getCreatedDate()
                ))
                .orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
