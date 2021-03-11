package com.celso.springmc.config;

import com.celso.springmc.services.DBService;
import com.celso.springmc.services.EmailService;
import com.celso.springmc.services.MockEMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDB();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEMailService();
    }
}
