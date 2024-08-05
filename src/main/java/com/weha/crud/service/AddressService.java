package com.weha.crud.service;

import com.weha.crud.dtos.AddressDTO;
import com.weha.crud.entity.AddressEntity;
import com.weha.crud.entity.UserEntity;
import com.weha.crud.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> findByUser(UserEntity user) {
        List<AddressDTO> addresses = new ArrayList<>();
        Optional<List<AddressEntity>> entities = addressRepository.findByUser(user);
        if (entities.isPresent()) {
            for (AddressEntity address : entities.get()) {
                addresses.add(new AddressDTO(
                        0,
                        address.getLine1(),
                        address.getLine2(),
                        address.getZipCode()));
            }
        }
        return addresses;
    }

    public void createAddress(UserEntity user, List<AddressDTO> addresses) {
        List<AddressEntity> data = new ArrayList<>();
        if (addresses != null) {
            for (AddressDTO dto : addresses) {
                AddressEntity address = new AddressEntity();
                address.setUser(user);
                address.setLine1(dto.line1());
                address.setLine2(dto.line2());
                address.setZipCode(dto.zipCode());
            }
        }
        addressRepository.saveAll(data);
    }
}
