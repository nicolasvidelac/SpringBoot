package com.example.JPAexample.services;

import com.example.JPAexample.repositories.UserRepository;
import com.example.JPAexample.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository _userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("usuario '" + username + "' no existe"));
    }
}
