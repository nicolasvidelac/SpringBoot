package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.myUser;

import java.util.Optional;

public interface UserRepository {

    Optional<myUser> findUserByUsername(String username);
}
