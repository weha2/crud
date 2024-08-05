package com.weha.crud.dtos;

public record AddressDTO(
        long id,
        String line1,
        String line2,
        String zipCode
) {
}
