package com.weha.crud.service;

import com.weha.crud.dtos.CreateUserDTO;
import com.weha.crud.dtos.ResponseUserDTO;
import com.weha.crud.entity.UserEntity;
import com.weha.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SocialService socialService;

    public UserService(UserRepository userRepository, SocialService socialService) {
        this.userRepository = userRepository;
        this.socialService = socialService;
    }

    public List<ResponseUserDTO> findUsers() {
        return userRepository
                .findAll()
                .stream().map(e -> entityToUserDTO(e).orElse(null))
                .toList();
    }

    public ResponseUserDTO findById(long id) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new Exception("Not found user");
        return entityToUserDTO(userEntity.get()).orElse(null);
    }

    public ResponseUserDTO createUser(CreateUserDTO req) {
        UserEntity user = new UserEntity();
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        UserEntity userEntity = userRepository.save(user);
        socialService.createSocial(userEntity, req.social());
        return entityToUserDTO(userEntity).orElse(null);
    }

    public ResponseUserDTO updateUser(long id, CreateUserDTO req) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new Exception("Not found user");
        UserEntity user = userEntity.get();
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setModifierDate(LocalDateTime.now());
        UserEntity updated = userRepository.save(user);
        socialService.updateSocial(updated.getSocial().getId(), updated, req.social());
        return entityToUserDTO(updated).orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private Optional<ResponseUserDTO> entityToUserDTO(UserEntity entity) {
        return Optional.of(entity)
                .map(u -> new ResponseUserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getCreatedDate(),
                        socialService.findByUser(u),
                        new ArrayList<>()
                ));
    }
}
