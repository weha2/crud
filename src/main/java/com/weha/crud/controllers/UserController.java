package com.weha.crud.controllers;

import com.weha.crud.dtos.CreateUserDTO;
import com.weha.crud.dtos.ResponseUserDTO;
import com.weha.crud.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "APIs for managing user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseUserDTO>> findUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @PostMapping("")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody CreateUserDTO req) {
        return ResponseEntity.ok(userService.createUser(req));
    }
}
