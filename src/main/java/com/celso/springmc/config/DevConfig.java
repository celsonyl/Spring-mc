package com.celso.springmc.config;

import com.celso.springmc.services.DBService;
import com.celso.springmc.services.EmailService;
import com.celso.springmc.services.SMTPEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!"create".equals(strategy))
            return false;

        dbService.instantiateTestDB();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SMTPEmailService();
    }
}
