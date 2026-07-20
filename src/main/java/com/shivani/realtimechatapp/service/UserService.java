package com.shivani.realtimechatapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.shivani.realtimechatapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @jakarta.annotation.PostConstruct
    public void resetUsersOnlineStatusOnStartup() {
        userRepository.resetAllUsersToOffline();
    }

    public void setUserOnlineStatus(String username, boolean isOnline) {
        userRepository.updateUserOnlineStatus(username, isOnline);
    }
}