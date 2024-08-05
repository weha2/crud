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

    @GetMapping("{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody CreateUserDTO req) {
        return ResponseEntity.ok(userService.createUser(req));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserDTO req) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, req));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(true);
    }
}
