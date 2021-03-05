package com.celso.springmc.services;

import com.celso.springmc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmEMail(Pedido obj);
    void sendEMail(SimpleMailMessage msg);
}
