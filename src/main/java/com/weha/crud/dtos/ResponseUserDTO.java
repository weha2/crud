package com.weha.crud.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseUserDTO(
        Long id,
        String fistName,
        String lastName,
        LocalDateTime createdDate,
        SocialDTO social,
        List<AddressDTO> addresses
) {
}
