package com.example.JPAexample.others.beans;

import com.example.JPAexample.models.Universidad;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class myBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Universidad getUTN() {
        return new Universidad(1, "Universidad Tecnologica Nacional", "San Martin", 69);
    }

    @Bean
    public Universidad getUBA() {
        return new Universidad(2, "Universidad de Buenos Aires", "Manuel Belgrano", 420);
    }

}
