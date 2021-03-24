package com.celso.springmc;

import com.celso.springmc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringmcApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}

