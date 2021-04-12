package com.example.JPAexample.controllers.thymeleaf;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String getLoginStartup() {
        return "login";
    }
}
