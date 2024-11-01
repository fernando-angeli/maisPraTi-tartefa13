package com.crud_user.api_user.controller;

import com.crud_user.api_user.dto.UserDto;
import com.crud_user.api_user.dto.UserInsertDto;
import com.crud_user.api_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity <List<UserDto>> findAll(Pageable pageable){
        List<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return userDto != null ? ResponseEntity.ok().body(userDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserInsertDto userDto){
        UserDto newUser = userService.insert(userDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(id, userDto);
        return updatedUser != null ? ResponseEntity.ok().body(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
