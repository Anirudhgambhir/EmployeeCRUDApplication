package com.example.anirudh.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonDI {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
