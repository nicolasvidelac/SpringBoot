package com.example.JPAexample.repositories;

import com.example.JPAexample.models.myUser;
import com.example.JPAexample.repositories.interfaces.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.JPAexample.enums.UserRoleEnum.*;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final PasswordEncoder _passwordEnconder;

    @Autowired
    public UserRepositoryImp(PasswordEncoder passwordEnconder) {
        _passwordEnconder = passwordEnconder;
    }

    private List<myUser> getUserApp() {
        List<myUser> myUsers = Lists.newArrayList(
                new myUser(
                        ALUMNO.getGrantedAuthorities(),
                        _passwordEnconder.encode("password"),
                        "alumno",
                        true,
                        true,
                        true,
                        true
                ),
                new myUser(
                        ADMIN.getGrantedAuthorities(),
                        _passwordEnconder.encode("password"),
                        "admin",
                        true,
                        true,
                        true,
                        true
                ),
                new myUser(
                        ADMINTRAINEE.getGrantedAuthorities(),
                        _passwordEnconder.encode("password"),
                        "admintr",
                        true,
                        true,
                        true,
                        true
                )
        );

        return myUsers;
    }

    @Override
    public Optional<myUser> findUserByUsername(String username) {
        return getUserApp().stream().filter(myUser -> username.equals(myUser.getUsername())).findFirst();
    }
}
