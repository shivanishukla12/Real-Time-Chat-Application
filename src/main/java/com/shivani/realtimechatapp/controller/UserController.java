package com.shivani.realtimechatapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivani.realtimechatapp.dtos.UserDTO;
import com.shivani.realtimechatapp.model.User;
import com.shivani.realtimechatapp.repository.UserRepository;
import com.shivani.realtimechatapp.service.AuthenticationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    @GetMapping("/online")
    public ResponseEntity<List<UserDTO>> getOnlineUsers() {
        List<User> onlineUsers = userRepository.findByIsOnlineTrue();
        List<UserDTO> onlineUserDTOs = onlineUsers.stream()
                .map(authenticationService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(onlineUserDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(authenticationService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }
}
