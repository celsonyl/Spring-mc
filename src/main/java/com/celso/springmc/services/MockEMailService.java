package com.celso.springmc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEMailService extends AbstractEmailService{

    private static final Logger log = LoggerFactory.getLogger(MockEMailService.class);

    @Override
    public void sendEMail(SimpleMailMessage msg) {
        log.info("Simulando");
        log.info(msg.toString());
        log.info("Funciono");

    }
}
