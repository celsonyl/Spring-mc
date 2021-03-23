package com.celso.springmc;

import com.celso.springmc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmcApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(SpringmcApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
            s3Service.uploadFile("E:\\ab1f3294-16d9-4285-907c-9475d740ffd8.jfif");

    }
}

