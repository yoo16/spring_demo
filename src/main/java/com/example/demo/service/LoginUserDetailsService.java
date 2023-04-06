package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.LoginUserDetails;
import com.example.demo.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    protected static Logger log = LoggerFactory.getLogger(LoginUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> loginUserOptional = userRepository.findByEmail(email);
        return loginUserOptional.map(loginUser -> new LoginUserDetails(loginUser))
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}
