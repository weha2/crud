package com.weha.crud.dtos;

import java.time.LocalDateTime;

public record ResponseUserDTO(
        Long id,
        String fistName,
        String lastName,
        LocalDateTime createdDate
) {
}
