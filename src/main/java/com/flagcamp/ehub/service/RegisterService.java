package com.flagcamp.ehub.service;

import com.flagcamp.ehub.exception.UserAlreadyExistException;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void add(User user) throws UserAlreadyExistException {
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistException("User already exists");
        }
        userRepository.save(user.setPassword(passwordEncoder.encode(user.getPassword())));
    }
}
