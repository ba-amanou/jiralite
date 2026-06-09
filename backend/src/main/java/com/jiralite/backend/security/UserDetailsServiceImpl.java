package com.jiralite.backend.security;

import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiralite.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(userId))
                .map(user -> new org.springframework.security.core.userdetails.User(user.getId().toString(),user.getPassword(),Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));
    }

}
