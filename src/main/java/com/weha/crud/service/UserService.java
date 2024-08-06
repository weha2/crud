package com.weha.crud.service;

import com.weha.crud.dtos.AddressDTO;
import com.weha.crud.dtos.CreateUserDTO;
import com.weha.crud.dtos.ResponseUserDTO;
import com.weha.crud.dtos.SocialDTO;
import com.weha.crud.entity.AddressEntity;
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
    private final AddressService addressService;

    public UserService(UserRepository userRepository, SocialService socialService, AddressService addressService) {
        this.userRepository = userRepository;
        this.socialService = socialService;
        this.addressService = addressService;
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

        // Create social
        socialService.createSocial(userEntity, req.social());

        // Create address
        addressService.updateAddress(userEntity, req.addresses());
        return entityToUserDTO(userEntity, true).orElse(null);
    }

    public ResponseUserDTO updateUser(long id, CreateUserDTO req) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new Exception("Not found user");
        UserEntity user = userEntity.get();
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setModifierDate(LocalDateTime.now());
        UserEntity updated = userRepository.save(user);

        // Update social
        socialService.updateSocial(updated.getSocial().getId(), updated, req.social());

        // Update address
        addressService.updateAddress(updated, req.addresses());
        return entityToUserDTO(updated, true).orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private Optional<ResponseUserDTO> entityToUserDTO(UserEntity entity) {
        return entityToUserDTO(entity, false);
    }

    private Optional<ResponseUserDTO> entityToUserDTO(UserEntity entity, boolean isCreate) {
        List<AddressDTO> addresses = isCreate ? addressService.findByUser(entity) : addressDTO(entity.getAddresses());
        SocialDTO social = socialService.findByUser(entity);
        return Optional.of(entity)
                .map(u -> new ResponseUserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getCreatedDate(),
                        social,
                        addresses
                ));
    }

    private List<AddressDTO> addressDTO(List<AddressEntity> entity) {
        List<AddressDTO> dto = new ArrayList<>();
        if (entity != null) {
            for (AddressEntity address : entity) {
                dto.add(new AddressDTO(
                        address.getId(),
                        address.getLine1(),
                        address.getLine2(),
                        address.getZipCode()
                ));
            }
        }
        return dto;
    }
}
