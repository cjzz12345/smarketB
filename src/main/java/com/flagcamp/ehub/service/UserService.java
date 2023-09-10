package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.UserAlreadyExistException;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public void add(User user) throws UserAlreadyExistException {
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistException("User already exists");
        }
        userRepository.save(user.setPassword(passwordEncoder.encode(user.getPassword())));
    }

    public String loginAndGenerateToken(User user){
        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch (AuthenticationException e){
            throw new UsernameNotFoundException("User does not exist");
        }
        if(auth == null || !auth.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }
        return jwtService.generateToken(user.getUsername());
    }
}
