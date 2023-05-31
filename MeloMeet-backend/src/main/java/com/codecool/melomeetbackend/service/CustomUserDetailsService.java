package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Username not found!"));
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword()).build();

        return userDetails;
    }
}
