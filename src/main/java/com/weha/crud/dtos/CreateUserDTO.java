package com.weha.crud.dtos;

import java.util.List;

public record CreateUserDTO(
        String firstName,
        String lastName,
        SocialDTO social,
        List<AddressDTO> addresses
) {
}
